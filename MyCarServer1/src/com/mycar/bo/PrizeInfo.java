package com.mycar.bo;

public class PrizeInfo {
	private int id;
	private String prizname;
	private String other;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrizname() {
		return prizname;
	}
	public void setPrizname(String prizname) {
		this.prizname = prizname;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	public PrizeInfo(int id, String prizname, String other) {
		super();
		this.id = id;
		this.prizname = prizname;
		this.other = other;
	}
	
	public PrizeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PrizeInfo [id=" + id + ", prizname=" + prizname + ", other="
				+ other + "]";
	}
	
}
