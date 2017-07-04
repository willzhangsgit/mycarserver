package com.mycar.bo;

public class CameraInfo {
	private int id;
	private String cameraname;
	private int camerastatus;
	private String remarks;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCameraname() {
		return cameraname;
	}
	public void setCameraname(String cameraname) {
		this.cameraname = cameraname;
	}
	public int getCamerastatus() {
		return camerastatus;
	}
	public void setCamerastatus(int camerastatus) {
		this.camerastatus = camerastatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public CameraInfo(int id, String cameraname, int camerastatus,
			String remarks) {
		super();
		this.id = id;
		this.cameraname = cameraname;
		this.camerastatus = camerastatus;
		this.remarks = remarks;
	}
	
	public CameraInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "CameraInfo [id=" + id + ", cameraname=" + cameraname
				+ ", camerastatus=" + camerastatus + ", remarks=" + remarks
				+ "]";
	}
	
}
