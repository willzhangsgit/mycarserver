package com.mycar.service;

import java.util.List;

import com.mycar.bo.*;



public interface ConfigService {
	//void save(St_city user);
	//boolean update(St_city user);
	//boolean delete(String id);
	ets_cis_config findById(String id);
	List<ets_cis_config> findAll();
}
