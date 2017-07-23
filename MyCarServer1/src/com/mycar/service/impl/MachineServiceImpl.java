package com.mycar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycar.bo.*;
import com.mycar.mapper.machineMapper;
import com.mycar.service.MachineService;


@Service
@Transactional
public class MachineServiceImpl implements MachineService {
	
	@Resource
	private machineMapper mapper;

	public List<MachineInfo> findAll() {
		List<MachineInfo> findAllList = mapper.findAll();
		return findAllList;
	}

	public MachineInfo findById(String id) {

		MachineInfo user = mapper.findById(String.valueOf(id));
		
		return user;
	}

	public int updateContorller(String userid, String id, String iscontrol) {
		int rtn = mapper.updateContorller(userid, iscontrol, id);
		return rtn;
	}
}
