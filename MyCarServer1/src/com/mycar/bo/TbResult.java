package com.mycar.bo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TbResult {
	private long result;
	private String err_msg;
	private int record_total;
	private String[] online_devices;
	private int device_status;
	private int device_enable;
	private String status;
	private CoinRecord[] records;
	private String order_code;
	private String order_status;
	private int coin_count;
	private String extra;
	
	
	public long getResult() {
		return result;
	}
	public void setResult(long result) {
		this.result = result;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public int getRecord_total() {
		return record_total;
	}
	public void setRecord_total(int record_total) {
		this.record_total = record_total;
	}
	public String[] getOnline_devices() {
		return online_devices;
	}
	public void setOnline_devices(String[] online_devices) {
		this.online_devices = online_devices;
	}
	public int getDevice_status() {
		return device_status;
	}
	public void setDevice_status(int device_status) {
		this.device_status = device_status;
	}
	public int getDevice_enable() {
		return device_enable;
	}
	public void setDevice_enable(int device_enable) {
		this.device_enable = device_enable;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CoinRecord[] getRecords() {
		return records;
	}
	public void setRecords(CoinRecord[] records) {
		this.records = records;
	}
	public String getOrder_code() {
		return order_code;
	}
	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public int getCoin_count() {
		return coin_count;
	}
	public void setCoin_count(int coin_count) {
		this.coin_count = coin_count;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public TbResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TbResult [result=" + result + ", err_msg=" + err_msg
				+ ", record_total=" + record_total + ", online_devices="
				+ Arrays.toString(online_devices) + ", device_status="
				+ device_status + ", device_enable=" + device_enable
				+ ", status=" + status + ", records="
				+ Arrays.toString(records) + ", order_code=" + order_code
				+ ", order_status=" + order_status + ", coin_count="
				+ coin_count + ", extra=" + extra + "]";
	}
}
