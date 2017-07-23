package com.mycar.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mycar.bo.*;
import com.mycar.service.TbCallBackService;


@Controller
@RequestMapping("/callback1")
public class TbCallBackController {

	@Autowired
	private TbCallBackService tbcallbackService;
	

	@RequestMapping(value = "/devicestatus_notify", method = RequestMethod.GET)
	@ResponseBody
	public CallBackResult devicestatus_notify(HttpServletRequest request){
		
		String action = request.getParameter("action");
		String device_code = request.getParameter("device_code");
		String device_status = request.getParameter("device_status");
		String sign = request.getParameter("sign");
		
		CallBackResult cbr = new CallBackResult();
		cbr.setResult(1);
		cbr.setErr_msg("");
		
		return cbr;
	}

}
