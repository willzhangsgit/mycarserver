package fancy.mycar.util;

import java.io.Serializable;
import java.util.Map;

public class _ResponseJson extends _GwResponse implements Serializable {
	private Map<String, Object> data;

	public _ResponseJson(Map<String, Object> data) {
		super();
		this.data= data;
	}

	public _ResponseJson(){
		super();
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
