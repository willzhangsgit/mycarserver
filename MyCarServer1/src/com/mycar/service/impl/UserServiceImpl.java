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

	public List<ets_cis_config> findAll() {
		List<ets_cis_config> findAllList = mapper.findAll();
		return findAllList;
	}

	public ets_cis_config findById(String id) {

		ets_cis_config user = mapper.findById(String.valueOf(id));
		
		return user;
	}
}
