package com.mycar.bo;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class CoinRecord {
	private String time_create;
	private String device_code;
	private int coin_count;
	
	public String getTime_create() {
		return time_create;
	}
	public void setTime_create(String time_create) {
		this.time_create = time_create;
	}
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public int getCoin_count() {
		return coin_count;
	}
	public void setCoin_count(int coin_count) {
		this.coin_count = coin_count;
	}
	
}
