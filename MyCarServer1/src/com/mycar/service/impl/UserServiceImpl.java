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
		wrtn.setResultMessage("注册成功");
		wrtn.addData("regrtn", String.valueOf(rtnReg));
		return wrtn;
	}
	
	public WsOut login(UserEnrollment user){
		WsOut wrtn = new WsOut();
		int rtnReg = mapper.login(user);
		if(rtnReg < 1){		
			wrtn.setResultCode(rtnReg);
			wrtn.setResultMessage("登录失败,错误的用户名或密码");
			UserEnrollment lUser = new UserEnrollment();
			lUser.setUserid(rtnReg);
			wrtn.addData("loginUser", lUser);
		}else{
			//UserEnrollment lUser = mapper.findById(String.valueOf(rtnReg));
			List<UserEnrollment> lUser = mapper.findByAccount(user.getAccounts());
			if(lUser.size() > 0){
				//账号登陆
				UserEnrollment rUser = lUser.get(0);
				wrtn.addData("loginUser", rUser);
				wrtn.setResultCode(1);
				wrtn.setResultMessage("登录成功");
			}else{
				lUser = mapper.findByPhone(user.getPhone());
				if(lUser.size() > 0){
					//手机号登陆
					UserEnrollment rUser = lUser.get(0);
					wrtn.addData("loginUser", rUser);
					wrtn.setResultCode(1);
					wrtn.setResultMessage("登录成功");
				}else{
					wrtn.addData("loginUser", null);
					wrtn.setResultCode(-1);
					wrtn.setResultMessage("登录失败,找不到对应的用户信息");
				}
			}
		}

		return wrtn;
	}
}
