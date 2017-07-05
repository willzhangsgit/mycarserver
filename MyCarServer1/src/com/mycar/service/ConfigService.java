package com.mycar.service;

import java.util.List;

import com.mycar.bo.*;



public interface ConfigService {
	ets_cis_config findById(String id);
	List<ets_cis_config> findAll();
}
