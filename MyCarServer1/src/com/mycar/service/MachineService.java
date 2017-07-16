package com.mycar.service;

import java.util.List;

import com.mycar.bo.*;



public interface MachineService {
	//void save(St_city user);
	//boolean update(St_city user);
	//boolean delete(String id);
	MachineInfo findById(String id);
	List<MachineInfo> findAll();
	int updateContorller(String userid, String id, String iscontrol);
}
