package fancy.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fancy.mycar.bo.ets_cis_config;
import fancy.mycar.ui.util.HttpUtil;

/**
 * Created by Will on 2017/5/23.
 */

public class MyLogin_FRM extends Activity {
	protected static final String TAG = "MyLogin_FRM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylogin_frm);
		initControl();

		initConfig();
	}

	private void initConfig() {
		String servname = "http://10.111.11.34:8091/MyCarServer1/config/getAllConfig/";
		JsonRequest request = new JsonObjectRequest(servname, new JSONObject(), new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				Log.v("data...", jsonObject.toString());
				try {
					String result = jsonObject.getString("resultCode");
					if(result.equals("1")){
						JSONArray realData = jsonObject.getJSONObject("data").getJSONArray("configList");
						EzvizApplication.configs = new Gson().fromJson(realData.toString(), new TypeToken<List<ets_cis_config>>(){}.getType());
						Log.i("configs", EzvizApplication.configs.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.v("data...","");
			}
		});

		EzvizApplication.getReqQueue().add(request);
	}


	private void initControl() {
		Button btnGeneal = (Button) this.findViewById(R.id.button1);
		Button btnQQ = (Button) this.findViewById(R.id.button2);
		Button btnWX = (Button) this.findViewById(R.id.button3);

		btnQQ.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				QQLogin();
			}
		});

		btnWX.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				WXLogin();
			}
		});
	}

	private void WXLogin() {
		try {
			SendAuth.Req req = new SendAuth.Req();
			req.scope = "snsapi_userinfo";
			req.state = "none";
			boolean wxlogin = EzvizApplication.mWXapi.sendReq(req);
			Log.d("wxlogin", "test");
		}catch (Exception ex){
			String err = ex.getMessage();
		}
	}

	private void QQLogin() {
		if (!EzvizApplication.mTencent.isSessionValid()) {
			EzvizApplication.mTencent.login(MyLogin_FRM.this, EzvizApplication.scope, EzvizApplication.loginListener);
		}
	}

	private String goLogin() {
		String atocken = "";
		try {
			String servname = "https://open.ys7.com/api/lapp/token/get";
			Map<String, String> params = new HashMap<String, String>();
			params.put("appKey", "d1c385cf66784cab907c8d131d53007b");
			params.put("appSecret", "1c90edb6a9c1b66aff2c959909cd7b6e");
			String ntocken = HttpUtil.submitPostData(servname, params, "utf-8");
			Log.d("ntocken", ntocken.toString());
			Map<String, Object> tockenInfo = parseData(ntocken.toString());
			Log.d("tockinfo", tockenInfo.toString());
			atocken = tockenInfo.get("accessToken").toString();
			Log.d("atocken", tockenInfo.toString());

		} catch (Exception ex) {
			String strex = ex.getMessage().toString();
		}
		return atocken;
	}

	private static Map<String, Object> parseData(String data) {
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		JsonObject rData = new JsonParser().parse(data).getAsJsonObject().getAsJsonObject("data");
		Map<String, Object> map = g.fromJson(rData.toString(), new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}
}
