package com.mycar.bo;

public class MachineInfo {
	
	private Integer id;

    private String machinename;
    
    private String deviceSerial;

    private String gtypeid;

    private Integer iscontrol;

    private String userid;

    private String networkip;

    private String networkstatus;

    private String controlport1;

    private String controlprot2;

    private String coindeviceid;

    private String coindevicestatus;

    private Integer coinquantity;

    private Integer cameraid;

    private String cameraname;

    private String camerastatus;

    private Integer prizid;

    private String prizname;

    private Integer enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMachinename() {
		return machinename;
	}

	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public String getGtypeid() {
		return gtypeid;
	}

	public void setGtypeid(String gtypeid) {
		this.gtypeid = gtypeid;
	}

	public Integer getIscontrol() {
		return iscontrol;
	}

	public void setIscontrol(Integer iscontrol) {
		this.iscontrol = iscontrol;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNetworkip() {
		return networkip;
	}

	public void setNetworkip(String networkip) {
		this.networkip = networkip;
	}

	public String getNetworkstatus() {
		return networkstatus;
	}

	public void setNetworkstatus(String networkstatus) {
		this.networkstatus = networkstatus;
	}

	public String getControlport1() {
		return controlport1;
	}

	public void setControlport1(String controlport1) {
		this.controlport1 = controlport1;
	}

	public String getControlprot2() {
		return controlprot2;
	}

	public void setControlprot2(String controlprot2) {
		this.controlprot2 = controlprot2;
	}

	public String getCoindeviceid() {
		return coindeviceid;
	}

	public void setCoindeviceid(String coindeviceid) {
		this.coindeviceid = coindeviceid;
	}

	public String getCoindevicestatus() {
		return coindevicestatus;
	}

	public void setCoindevicestatus(String coindevicestatus) {
		this.coindevicestatus = coindevicestatus;
	}

	public Integer getCoinquantity() {
		return coinquantity;
	}

	public void setCoinquantity(Integer coinquantity) {
		this.coinquantity = coinquantity;
	}

	public Integer getCameraid() {
		return cameraid;
	}

	public void setCameraid(Integer cameraid) {
		this.cameraid = cameraid;
	}

	public String getCameraname() {
		return cameraname;
	}

	public void setCameraname(String cameraname) {
		this.cameraname = cameraname;
	}

	public String getCamerastatus() {
		return camerastatus;
	}

	public void setCamerastatus(String camerastatus) {
		this.camerastatus = camerastatus;
	}

	public Integer getPrizid() {
		return prizid;
	}

	public void setPrizid(Integer prizid) {
		this.prizid = prizid;
	}

	public String getPrizname() {
		return prizname;
	}

	public void setPrizname(String prizname) {
		this.prizname = prizname;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	

	public MachineInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MachineInfo(Integer id, String machinename, String deviceSerial, String userid,
			Integer enabled) {
		super();
		this.id = id;
		this.machinename = machinename;
		this.deviceSerial = deviceSerial;
		this.userid = userid;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "MachineInfo [id=" + id + ", machinename=" + machinename
				+ ", deviceSerial=" + deviceSerial + ", gtypeid=" + gtypeid
				+ ", iscontrol=" + iscontrol + ", userid=" + userid
				+ ", networkip=" + networkip + ", networkstatus="
				+ networkstatus + ", controlport1=" + controlport1
				+ ", controlprot2=" + controlprot2 + ", coindeviceid="
				+ coindeviceid + ", coindevicestatus=" + coindevicestatus
				+ ", coinquantity=" + coinquantity + ", cameraid=" + cameraid
				+ ", cameraname=" + cameraname + ", camerastatus="
				+ camerastatus + ", prizid=" + prizid + ", prizname="
				+ prizname + ", enabled=" + enabled + "]";
	}
    
}
