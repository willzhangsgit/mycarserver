package com.mycar.controller;

import java.io.IOException;
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
import com.mycar.service.ConfigService;


@Controller
@RequestMapping("/config")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	

	@RequestMapping("/getAllConfig")
	@ResponseBody
	public String getAllConfig(HttpServletRequest request){
		
		List<ets_cis_config> findAll = configService.findAll();
		
		request.setAttribute("configList", findAll);
		
		Gson gson = new Gson();
		String configs = gson.toJson(findAll);
		
		return configs;
	}
	

	@RequestMapping("/getCvById")
	@ResponseBody
	public String getConfigById(String id,HttpServletRequest request){
		ets_cis_config ecs = configService.findById(String.valueOf(id));
		request.setAttribute("config", ecs);
		if(ecs == null)
			return "查无此记录";
		
		return ecs.cfg_value;
	}

}
