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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mycar.bo.*;
import com.mycar.service.UserService;
import com.mycar.util.HttpUtil;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	

	@RequestMapping("/getAllUser")
	@ResponseBody
	public String getAllUser(HttpServletRequest request){
		
		List<UserEnrollment> findAll = userService.findAll();
		
		request.setAttribute("userList", findAll);
		
		Gson gson = new Gson();
		String users = gson.toJson(findAll);
		
		return users;
	}
	

	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request){
		
		return "/addUser";
	}

	@RequestMapping("/reg")
	@ResponseBody
	public WsOut regUser(HttpServletRequest request){
		try {
			InputStreamReader isr;
			isr = new InputStreamReader(request.getInputStream(), "utf-8");
			Gson gson = new Gson();
			UserEnrollment u = gson.fromJson(isr, UserEnrollment.class);

			if(u.getAccounts()!=null && !u.getAccounts().equals("")){
				WsOut sysuser = searchUser(u.getAccounts(), u.getPhone());
				if(sysuser.getResultCode() != 1){
					return sysuser;
				}
			
				WsOut regrtn = userService.reg(u);
				return regrtn;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@RequestMapping("/updateUser")
	public String updateUser(UserEnrollment user,HttpServletRequest request){
		
		
/*		if(userService.update(user)){
			user = userService.findById(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}*/
		return null;
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(String id,HttpServletRequest request){
		UserEnrollment ecs = userService.findById(String.valueOf(id));
		request.setAttribute("user", ecs);
		if(ecs == null)
			return "查无此记录";
		
		return ecs.getAccounts();
	}
	
	@RequestMapping("/getUserByC/{account}/{phoneno}")
	@ResponseBody
	public WsOut getUser(@PathVariable String account, @PathVariable String phoneno){
		return searchUser(account, phoneno);
	}


	private WsOut searchUser(String account, String phoneno) {
		WsOut rtnWo = new WsOut();
		rtnWo.setResultCode(1);
		List<UserEnrollment> findAc = userService.findUserByAccount(account);
		if(findAc.size() > 0){
			rtnWo.setResultCode(-2);
			rtnWo.setResultMessage("用户名已注册");
		}
		List<UserEnrollment> findPhone = userService.findUserByPhone(phoneno);
		if(findAc.size() > 0){
			rtnWo.setResultCode(-3);
			rtnWo.setResultMessage("手机号已注册");
		}
		
		return rtnWo;
	}

	@RequestMapping("/delUser")
	public void delUser(int id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		
/*		if(userService.delete(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}
	
	@RequestMapping("/sendVerifyCode/{phoneno}")
	@ResponseBody
	public String sendVerifyCode(@PathVariable String phoneno){
		System.out.println(phoneno + "发送注册短信");
		HttpUtil httpRequest = new HttpUtil();
		httpRequest.getBalance("POST");
		httpRequest.sendSms("POST");
		return "1";
	}
}
