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
import com.google.gson.JsonObject;
import com.mycar.bo.*;
import com.mycar.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	

	@RequestMapping("/getAllUser")
	@ResponseBody
	public StdOut getAllUser(HttpServletRequest request){
		
		List<ets_cis_config> findAll = userService.findAll();
		
		request.setAttribute("userList", findAll);
		
		StdOut sto = new StdOut();
		sto.setResultCode(1);
		sto.setResultMessage("");
		sto.addData("userList", findAll);
		
		return sto;
	}
	

	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request){
		
		return "/addUser";
	}

	@RequestMapping("/addUser")
	public String addUser(ets_cis_config user,HttpServletRequest request){
/*		userService.save(user);
		return "redirect:/user/getAllUser";*/
		return null;
	}
	

	@RequestMapping("/updateUser")
	public String updateUser(ets_cis_config user,HttpServletRequest request){
		
		
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
		ets_cis_config ecs = userService.findById(String.valueOf(id));
		request.setAttribute("user", ecs);
		if(ecs == null)
			return "查无此记录";
		
		return ecs.cfg_desc;
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
}
