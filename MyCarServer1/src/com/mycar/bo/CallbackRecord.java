package com.mycar.bo;

import java.sql.Date;

public class CallbackRecord {
    private Long id;

    private String action;

    private String deviceCode;

    private String deviceStatus;

    private String sign;

    private String orderCode;

    private Integer coinCount;

    private String timeCreate;

    private String status;

    private Integer result;

    private String errMsg;

    private Date callbacktime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(Integer coinCount) {
		this.coinCount = coinCount;
	}

	public String getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Date getCallbacktime() {
		return callbacktime;
	}

	public void setCallbacktime(Date callbacktime) {
		this.callbacktime = callbacktime;
	}
	
	

	public CallbackRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CallbackRecord(Long id, String action, String deviceCode,
			String deviceStatus, String sign, String orderCode,
			Integer coinCount, String timeCreate) {
		super();
		this.id = id;
		this.action = action;
		this.deviceCode = deviceCode;
		this.deviceStatus = deviceStatus;
		this.sign = sign;
		this.orderCode = orderCode;
		this.coinCount = coinCount;
		this.timeCreate = timeCreate;
	}

	@Override
	public String toString() {
		return "CallbackRecord [id=" + id + ", action=" + action
				+ ", deviceCode=" + deviceCode + ", deviceStatus="
				+ deviceStatus + ", sign=" + sign + ", orderCode=" + orderCode
				+ ", coinCount=" + coinCount + ", timeCreate=" + timeCreate
				+ ", status=" + status + ", result=" + result + ", errMsg="
				+ errMsg + ", callbacktime=" + callbacktime + "]";
	}
}
