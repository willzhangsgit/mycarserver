package fancy.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fancy.mycar.ui.util.HttpUtil;

/**
 * Created by Will on 2017/5/23.
 */

public class MyLogin_FRM extends Activity {
	protected static final String TAG = "CameraListActivity";

	public final static int TAG_CLICK_PLAY = 1;
	public final static int TAG_CLICK_REMOTE_PLAY_BACK = 2;


	private final static int LOAD_MY_DEVICE = 0;

	public RequestQueue mQueue;

	private static final String APPID = "1105233460";
	private Tencent mTencent;
	private IUiListener loginListener;
	private String scope;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylogin_frm);
		mQueue = Volley.newRequestQueue(this);
		initapi();
		initControl();
		//testServer();
	}

	private void initapi() {
		mTencent = Tencent.createInstance(APPID, MyLogin_FRM.this);
		//要所有权限，不用再次申请增量权限，这里不要设置成get_user_info,add_t
		scope = "all";
		loginListener = new IUiListener() {

			@Override
			public void onError(UiError arg0) {
				// TODO Auto-generated method stub
				String strerr = arg0.errorMessage;
			}

			@Override
			public void onComplete(Object value) {
				// TODO Auto-generated method stub

				System.out.println("有数据返回..");
				if (value == null) {
					return;
				}

				try {
					JSONObject jo = (JSONObject) value;

					String msg = jo.getString("msg");

					System.out.println("json=" + String.valueOf(jo));

					System.out.println("msg="+msg);
					if ("sucess".equals(msg)) {
						Toast.makeText(MyLogin_FRM.this, "登录成功",
								Toast.LENGTH_LONG).show();

						String openID = jo.getString("openid");
						String accessToken = jo.getString("access_token");
						String expires = jo.getString("expires_in");
						mTencent.setOpenId(openID);
						mTencent.setAccessToken(accessToken, expires);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		};
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
	}

	private void QQLogin() {
		if (!mTencent.isSessionValid()) {
			mTencent.login(MyLogin_FRM.this, scope, loginListener);
		}
	}

	private void testServer() {
//		String servname = "http://127.0.0.1:8091/MyCarServer1/user/getAllUser/";
////		String servname = "http://172.18.66.27:8080/NS/servlet/ScheServlet";
//		JsonRequest request = new JsonObjectRequest(servname, new JSONObject(), new Response.Listener<JSONObject>() {
//			@Override
//			public void onResponse(JSONObject jsonObject) {
//				Log.v("data...", jsonObject.toString());
//				try {
////					String result = jsonObject.getString("resultCode");
////					if(result.equals("1")){
////						JSONObject realData = jsonObject.getJSONObject("data").getJSONObject("doctorShedule");
//						ets_cis_config schedule = new Gson().fromJson(jsonObject.toString(), ets_cis_config.class);
//						Log.i("data...",schedule.toString());
////					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}, new Response.ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError volleyError) {
//				Log.v("data...","");
//			}
//		});
		try {
			StringRequest stringRequest = new StringRequest("http://192.168.1.105:8091/MyCarServer1/user/getAllUser",
					new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							Log.d("TAG", "gw test it");
						}
					}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.e("TAG", error.getMessage(), error);
				}
			});

			mQueue.add(stringRequest);
		} catch (Exception ex) {
			String exerr = ex.getMessage();
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
