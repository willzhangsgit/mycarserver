package fancy.mycar.bo;

import java.io.Serializable;

/**
 * Created by Will on 2017/7/1.
 */

public class UserEnrollment implements Serializable {
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
}
