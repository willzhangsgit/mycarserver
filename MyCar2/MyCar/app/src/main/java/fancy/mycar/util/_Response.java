package fancy.mycar.util;

import java.util.Map;

public class _Response extends  _GwResponse{
	private Map<String, String> data;
	
	public _Response(Map<String, String> data) {
		super();
		this.data = data;
	}

	public _Response(){
		super();
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
}
