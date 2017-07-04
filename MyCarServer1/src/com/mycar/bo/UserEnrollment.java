package com.mycar.bo;

import java.sql.Date;

public class UserEnrollment {
	private int userid;
	private String accounts;
	private String password;
	private String gender;
	private String faceid;
	private int alllogintimes;
	private String registerdate;
	private String lastlogindate;
	private String registerip;
	private String lastloginip;
	private String question;
	private String answer;
	private String InviteCode;
	private String Other;
	private String phone;
	private String zipcode;
	private String address;
	private String mailaddress;
	private String receiver;
	private String mobile;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFaceid() {
		return faceid;
	}
	public void setFaceid(String faceid) {
		this.faceid = faceid;
	}
	public int getAlllogintimes() {
		return alllogintimes;
	}
	public void setAlllogintimes(int alllogintimes) {
		this.alllogintimes = alllogintimes;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(String lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public String getRegisterip() {
		return registerip;
	}
	public void setRegisterip(String registerip) {
		this.registerip = registerip;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getInviteCode() {
		return InviteCode;
	}
	public void setInviteCode(String inviteCode) {
		InviteCode = inviteCode;
	}
	public String getOther() {
		return Other;
	}
	public void setOther(String other) {
		Other = other;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMailaddress() {
		return mailaddress;
	}
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public UserEnrollment(int userid, String accounts, String password,
			String gender, String faceid, int alllogintimes,
			String registerdate, String lastlogindate, String registerip,
			String lastloginip, String question, String answer,
			String inviteCode, String other, String phone, String zipcode,
			String address, String mailaddress, String receiver, String mobile) {
		super();
		this.userid = userid;
		this.accounts = accounts;
		this.password = password;
		this.gender = gender;
		this.faceid = faceid;
		this.alllogintimes = alllogintimes;
		this.registerdate = registerdate;
		this.lastlogindate = lastlogindate;
		this.registerip = registerip;
		this.lastloginip = lastloginip;
		this.question = question;
		this.answer = answer;
		InviteCode = inviteCode;
		Other = other;
		this.phone = phone;
		this.zipcode = zipcode;
		this.address = address;
		this.mailaddress = mailaddress;
		this.receiver = receiver;
		this.mobile = mobile;
	}
	public UserEnrollment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "UserEnrollment [userid=" + userid + ", accounts=" + accounts
				+ ", password=" + password + ", gender=" + gender + ", faceid="
				+ faceid + ", alllogintimes=" + alllogintimes
				+ ", registerdate=" + registerdate + ", lastlogindate="
				+ lastlogindate + ", registerip=" + registerip
				+ ", lastloginip=" + lastloginip + ", question=" + question
				+ ", answer=" + answer + ", InviteCode=" + InviteCode
				+ ", Other=" + Other + ", phone=" + phone + ", zipcode="
				+ zipcode + ", address=" + address + ", mailaddress="
				+ mailaddress + ", receiver=" + receiver + ", mobile=" + mobile
				+ "]";
	}
	
	
}
