package com.mycar.bo;

import java.util.HashMap;
import java.util.Map;

public class StdOut {
	private long resultCode;
	private String resultMessage;
	Map<String, Object> data = new HashMap<String, Object>();

	public long getResultCode() {
		return resultCode;
	}

	public void setResultCode(long resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void addData(String key, Object val) {
		data.put(key, val);
	}
}
