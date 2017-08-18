package com.mycar.bo;

public class ets_cis_config {
	public String cfg_id;
	public String cfg_name;
	public String cfg_desc;
	public String cfg_value;
	public String enabled;
	public String cfg_ext1;
	
	
	public ets_cis_config() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCfg_id() {
		return cfg_id;
	}
	public void setCfg_id(String cfg_id) {
		this.cfg_id = cfg_id;
	}
	public String getCfg_name() {
		return cfg_name;
	}
	public void setCfg_name(String cfg_name) {
		this.cfg_name = cfg_name;
	}
	public String getCfg_desc() {
		return cfg_desc;
	}
	public void setCfg_desc(String cfg_desc) {
		this.cfg_desc = cfg_desc;
	}
	public String getCfg_value() {
		return cfg_value;
	}
	public void setCfg_value(String cfg_value) {
		this.cfg_value = cfg_value;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getCfg_ext1() {
		return cfg_ext1;
	}

	public void setCfg_ext1(String cfg_ext1) {
		this.cfg_ext1 = cfg_ext1;
	}

	public ets_cis_config(String cfg_id, String cfg_name, String cfg_desc,
			String cfg_value, String enabled, String cfg_ext1) {
		super();
		this.cfg_id = cfg_id;
		this.cfg_name = cfg_name;
		this.cfg_desc = cfg_desc;
		this.cfg_value = cfg_value;
		this.enabled = enabled;
		this.cfg_ext1 = cfg_ext1;
	}

	@Override
	public String toString() {
		return "ets_cis_config [cfg_id=" + cfg_id + ", cfg_name=" + cfg_name
				+ ", cfg_desc=" + cfg_desc + ", cfg_value=" + cfg_value
				+ ", enabled=" + enabled + ", cfg_ext1=" + cfg_ext1 + "]";
	}

}
