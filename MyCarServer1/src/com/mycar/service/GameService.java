package com.mycar.service;

import java.util.List;

import com.mycar.bo.*;



public interface GameService {
	//void save(St_city user);
	//boolean update(St_city user);
	//boolean delete(String id);
	GameInfo findById(String id);
	List<GameInfo> findAll();
}
