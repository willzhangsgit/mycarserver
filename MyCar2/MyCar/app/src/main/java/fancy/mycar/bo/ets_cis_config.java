package fancy.mycar.bo;

import java.io.Serializable;

/**
 * Created by Will on 2017/7/1.
 */

public class ets_cis_config implements Serializable {
	public String cfg_id;
	public String cfg_name;
	public String cfg_desc;
	public String cfg_value;
	public String enabled;

	public String getCfg_desc() {
		return cfg_desc;
	}

	public void setCfg_desc(String cfg_desc) {
		this.cfg_desc = cfg_desc;
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
}
