package com.mycar.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mycar.bo.*;
import com.mycar.service.MachineService;

@Controller
@RequestMapping("/machine")
public class MachineController {

	@Autowired
	private MachineService machineService;
	

	@RequestMapping("/getAllMac")
	@ResponseBody
	public WsOut getAllConfig(HttpServletRequest request){
		
		List<MachineInfo> findAll = machineService.findAll();
		
		request.setAttribute("macList", findAll);
		
		Gson gson = new Gson();
		String macs = gson.toJson(findAll);
		
		WsOut wo = new WsOut();
		wo.setResultCode(1);
		wo.addData("macList", findAll);
		
		return wo;
	}
	

	@RequestMapping("/updateContorller")
	public int updateUser(MachineInfo mac,HttpServletRequest request){
		try {
			InputStreamReader isr;
			isr = new InputStreamReader(request.getInputStream(), "utf-8");
			Gson gson = new Gson();
			MachineInfo u = gson.fromJson(isr, MachineInfo.class);

			int rtn = machineService.updateContorller(u.getUserid().toString(), u.getId().toString(), u.getIscontrol().toString());
			return rtn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@RequestMapping("/getMac")
	@ResponseBody
	public WsOut getMac(String id,HttpServletRequest request){
		MachineInfo ecs = machineService.findById(String.valueOf(id));
		request.setAttribute("mac", ecs);

		WsOut wo = new WsOut();
		wo.setResultCode(1);
		wo.addData("macInfo", ecs);
		
		return wo;
	}
}
