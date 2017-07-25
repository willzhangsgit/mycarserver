package com.mycar.bo;

import java.sql.Date;

public class CallbackRecord {
    private Long id;

    private String action;

    private String device_code;

    private String device_status;

    private String sign;

    private String order_code;

    private Integer coin_count;

    private String time_create;

    private String status;

    private Integer result;

    private String err_msg;

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

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public Integer getCoin_count() {
		return coin_count;
	}

	public void setCoin_count(Integer coin_count) {
		this.coin_count = coin_count;
	}

	public String getTime_create() {
		return time_create;
	}

	public void setTime_create(String time_create) {
		this.time_create = time_create;
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

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
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

	@Override
	public String toString() {
		return "CallbackRecord [id=" + id + ", action=" + action
				+ ", device_code=" + device_code + ", device_status="
				+ device_status + ", sign=" + sign + ", order_code="
				+ order_code + ", coin_count=" + coin_count + ", time_create="
				+ time_create + ", status=" + status + ", result=" + result
				+ ", err_msg=" + err_msg + ", callbacktime=" + callbacktime
				+ "]";
	}
}
