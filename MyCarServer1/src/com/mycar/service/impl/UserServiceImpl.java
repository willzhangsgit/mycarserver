package com.mycar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycar.bo.*;
import com.mycar.mapper.userMapper;
import com.mycar.service.UserService;




@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private userMapper mapper;

	public List<UserEnrollment> findAll() {
		List<UserEnrollment> findAllList = mapper.findAll();
		return findAllList;
	}

	public UserEnrollment findById(String id) {

		UserEnrollment user = mapper.findById(String.valueOf(id));
		
		return user;
	}
	
	public List<UserEnrollment> findUserByAccount(String Acc) {
		List<UserEnrollment> findAllList = mapper.findByAccount(Acc);
		return findAllList;
	}
	
	public List<UserEnrollment> findUserByPhone(String Phone) {
		List<UserEnrollment> findAllList = mapper.findByPhone(Phone);
		return findAllList;
	}
	
	public WsOut reg(UserEnrollment user){
		WsOut wrtn = new WsOut();
		int rtnReg = mapper.reg(user);
		wrtn.setResultCode(1);
		wrtn.setResultMessage("×¢²á³É¹¦");
		wrtn.addData("regrtn", String.valueOf(rtnReg));
		return wrtn;
	}
}
