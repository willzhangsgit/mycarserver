package com.mycar.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycar.bo.*;
import com.mycar.mapper.tbcallbackMapper;
import com.mycar.service.TbCallBackService;



@Service
@Transactional
public class TbCallBackServiceImpl implements TbCallBackService {
	
	@Resource
	private tbcallbackMapper mapper;

	@Override
	public List<CallbackRecord> findByC(String action, String code, int flag) {
		// TODO Auto-generated method stub
		return mapper.findByC(action, code, flag);
	}

	@Override
	public int saveCallBackRecord(CallbackRecord cbr) {
		// TODO Auto-generated method stub
		return mapper.saveCallBackRecord(cbr);
	}

}
