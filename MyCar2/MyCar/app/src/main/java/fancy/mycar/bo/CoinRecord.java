package fancy.mycar.bo;

import java.io.Serializable;

/**
 * Created by Will on 2017/7/1.
 */

public class CoinRecord implements Serializable {
	private String time_create;
	private String device_code;
	private int coin_count;

	public int getCoin_count() {
		return coin_count;
	}

	public void setCoin_count(int coin_count) {
		this.coin_count = coin_count;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getTime_create() {
		return time_create;
	}

	public void setTime_create(String time_create) {
		this.time_create = time_create;
	}
}
