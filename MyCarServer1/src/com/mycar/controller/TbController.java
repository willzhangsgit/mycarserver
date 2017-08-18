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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mycar.bo.*;
import com.mycar.service.ConfigService;
import com.mycar.util.HttpUtil;
import com.mycar.util.ToolUtil;


@Controller
@RequestMapping("/tbApi")
public class TbController {

	//@Autowired
	//private TbCallBackService tbcallbackService;
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/onlinedevice", method = RequestMethod.GET)
	@ResponseBody
	public WsOut onlinedevice(HttpServletRequest request){
		
		String server_url = "";
		String sjbh = "";
		String sbbh = "";
		try{
			ets_cis_config sc = configService.findById("tb_serverurl");
			if(sc != null){
				server_url = sc.getCfg_value();
				sc = configService.findById("tb_sjbh");
				sjbh = sc.getCfg_value();
				sc = configService.findById("tb_sbbh");
				sbbh = sc.getCfg_value();
			}
			if(!server_url.equals("")){
				String extend_str = "";
				server_url += "/DeviceOpera.svc/OnlineDevice?";
				extend_str = "affiliate_code="+sjbh;
				String nonce_str = ToolUtil.getRandomString(10);
				extend_str += "&nonce_str=" + nonce_str;
				String api_key = ToolUtil.getProConfig("tb.api.apisecret");
				extend_str += "&key=" + api_key;
				String sign = ToolUtil.string2MD5(extend_str);
				extend_str += "&sign="+sign;
				server_url += extend_str;
				
				HttpUtil hutil = new HttpUtil();
				String rtn = hutil.requestGet(server_url, null);
				System.out.println("rtn_str:" + rtn);
				String json=rtn.substring(1,rtn.length()-1).replaceAll("\\\\","");
				Gson gson = new Gson();
				TbResult u = gson.fromJson(json, TbResult.class);
				
				WsOut cbrtn = new WsOut();
				cbrtn.setResultCode(1);
				cbrtn.setResultMessage("");
				cbrtn.addData("tb_info", u);
				return cbrtn;
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return null;
	}
}
