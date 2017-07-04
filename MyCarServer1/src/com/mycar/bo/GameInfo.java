package com.mycar.bo;

public class GameInfo {
	public String id;
	public String gname;
	public String gtypeid;
	public String processname;
	public String enable;
	public String remarks;
	
	
	public GameInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getGname() {
		return gname;
	}


	public void setGname(String gname) {
		this.gname = gname;
	}


	public String getGtypeid() {
		return gtypeid;
	}


	public void setGtypeid(String gtypeid) {
		this.gtypeid = gtypeid;
	}


	public String getProcessname() {
		return processname;
	}


	public void setProcessname(String processname) {
		this.processname = processname;
	}


	public String getEnable() {
		return enable;
	}


	public void setEnable(String enable) {
		this.enable = enable;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public GameInfo(String id, String gname, String gtypeid,
			String processname, String enable, String remarks) {
		super();
		this.id = id;
		this.gname = gname;
		this.gtypeid = gtypeid;
		this.processname = processname;
		this.enable = enable;
		this.remarks = remarks;
	}


	@Override
	public String toString() {
		return "GameInfo [id=" + id + ", gname=" + gname + ", gtypeid="
				+ gtypeid + ", processname=" + processname + ", enable="
				+ enable + ", remarks=" + remarks + "]";
	}

}
