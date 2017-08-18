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
		
		if(action!=null && !action.equals("")){
			CallbackRecord cbr = new CallbackRecord();
			cbr.setAction(action);
			cbr.setDevice_code(device_code);
			cbr.setDevice_status(device_status);
			cbr.setSign(sign);
			
			tbcallbackService.saveCallBackRecord(cbr);
		}
		
		CallBackResult cbrtn = new CallBackResult();
		cbrtn.setResult(1);
		cbrtn.setErr_msg("");
		
		return cbrtn;
	}

	@RequestMapping(value = "/devicestatus_get", method = RequestMethod.GET)
	@ResponseBody
	public WsOut devicestatus_get(HttpServletRequest request){
		String action = request.getParameter("action");
		String device_code = request.getParameter("device_code");
		int flag = 2;
		if(action != null && !action.equals("")){
			flag = 1;
		}
		
		List<CallbackRecord> lcr = tbcallbackService.findByC(action, device_code, flag);
		
		WsOut wo = new WsOut();
		wo.setResultCode(1);
		wo.addData("callbackList", lcr);
		
		return wo;
	}
	
	@RequestMapping(value = "/payout_notify", method = RequestMethod.GET)
	@ResponseBody
	public CallBackResult payout_notify(HttpServletRequest request){
		
		String action = request.getParameter("action");
		String order_code = request.getParameter("order_code");
		String coin_count = request.getParameter("coin_count");
		String sign = request.getParameter("sign");
		
		if(action!=null && !action.equals("")){
			CallbackRecord cbr = new CallbackRecord();
			cbr.setAction(action);
			cbr.setOrder_code(order_code);
			cbr.setCoin_count(Integer.valueOf(coin_count));
			cbr.setSign(sign);
			
			tbcallbackService.saveCallBackRecord(cbr);
		}
		
		CallBackResult cbrtn = new CallBackResult();
		cbrtn.setResult(1);
		cbrtn.setErr_msg("");
		
		return cbrtn;
	}

	@RequestMapping(value = "/payout_get", method = RequestMethod.GET)
	@ResponseBody
	public WsOut payout_get(HttpServletRequest request){
		String action = request.getParameter("action");
		String order_code = request.getParameter("order_code");
		int flag = 3;
		if(action != null && !action.equals("")){
			flag = 1;
		}
		
		List<CallbackRecord> lcr = tbcallbackService.findByC(action, order_code, flag);
		
		WsOut wo = new WsOut();
		wo.setResultCode(1);
		wo.addData("callbackList", lcr);
		
		return wo;
	}
	
	@RequestMapping(value = "/coin_notify", method = RequestMethod.GET)
	@ResponseBody
	public CallBackResult coin_notify(HttpServletRequest request){
		
		String action = request.getParameter("action");
		String device_code = request.getParameter("device_code");
		String time_create = request.getParameter("time_create");
		String status = request.getParameter("status");		
		String coin_count = request.getParameter("coin_count");
		String sign = request.getParameter("sign");
		
		if(action!=null && !action.equals("")){
			CallbackRecord cbr = new CallbackRecord();
			cbr.setAction(action);
			cbr.setDevice_code(device_code);
			cbr.setTime_create(time_create);
			cbr.setStatus(status);
			cbr.setCoin_count(Integer.valueOf(coin_count));
			cbr.setSign(sign);
			
			tbcallbackService.saveCallBackRecord(cbr);
		}
		
		CallBackResult cbrtn = new CallBackResult();
		cbrtn.setResult(1);
		cbrtn.setErr_msg("");
		
		return cbrtn;
	}
}
