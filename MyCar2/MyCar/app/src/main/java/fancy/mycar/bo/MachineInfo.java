package fancy.mycar.bo;

import java.io.Serializable;

/**
 * Created by Will on 2017/7/1.
 */

public class MachineInfo implements Serializable {
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

	private  String deviceKey;

	private String controlDomain;

	private String deviceIcon;

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

	public String getDeviceSerial() {
		return deviceSerial;
	}

	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getGtypeid() {
		return gtypeid;
	}

	public void setGtypeid(String gtypeid) {
		this.gtypeid = gtypeid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIscontrol() {
		return iscontrol;
	}

	public void setIscontrol(Integer iscontrol) {
		this.iscontrol = iscontrol;
	}

	public String getMachinename() {
		return machinename;
	}

	public void setMachinename(String machinename) {
		this.machinename = machinename;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public String getControlDomain() {
		return controlDomain;
	}

	public void setControlDomain(String controlDomain) {
		this.controlDomain = controlDomain;
	}

	public String getDeviceIcon() {
		return deviceIcon;
	}

	public void setDeviceIcon(String deviceIcon) {
		this.deviceIcon = deviceIcon;
	}
}
