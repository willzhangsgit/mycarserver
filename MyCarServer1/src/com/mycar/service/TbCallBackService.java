package com.mycar.service;

import java.util.List;

import com.mycar.bo.*;



public interface TbCallBackService {
	List<CallbackRecord> findByC(String action, String code, int falg);
	int saveCallBackRecord(CallbackRecord cbr);
}
