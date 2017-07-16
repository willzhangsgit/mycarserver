package com.mycar.service;

import java.util.List;

import com.mycar.bo.*;



public interface UserService {
	UserEnrollment findById(String id);
	List<UserEnrollment> findAll();
	List<UserEnrollment> findUserByAccount(String acc);
	List<UserEnrollment> findUserByPhone(String phone);
	WsOut reg(UserEnrollment user);
	WsOut login(UserEnrollment user);
}
