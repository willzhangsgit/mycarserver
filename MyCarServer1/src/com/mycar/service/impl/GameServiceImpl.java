package com.mycar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycar.bo.*;
import com.mycar.mapper.gameMapper;
import com.mycar.service.GameService;




@Service
@Transactional
public class GameServiceImpl implements GameService {
	
	@Resource
	private gameMapper mapper;

	public List<GameInfo> findAll() {
		List<GameInfo> findAllList = mapper.findAll();
		return findAllList;
	}

	public GameInfo findById(String id) {

		GameInfo user = mapper.findById(String.valueOf(id));
		
		return user;
	}
}
