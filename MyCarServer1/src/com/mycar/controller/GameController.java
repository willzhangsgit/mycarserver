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
import com.mycar.service.GameService;


@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;
	

	@RequestMapping("/getAllGame")
	@ResponseBody
	public String getAllUser(HttpServletRequest request){
		
		List<GameInfo> findAll = gameService.findAll();
		
		request.setAttribute("gameList", findAll);
		
		Gson gson = new Gson();
		String games = gson.toJson(findAll);
		
		return games;
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

	@RequestMapping("/getGame")
	@ResponseBody
	public String getUser(String id,HttpServletRequest request){
		GameInfo ecs = gameService.findById(String.valueOf(id));
		request.setAttribute("game", ecs);
		if(ecs == null)
			return "查无此记录";
		
		return ecs.gname;
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
