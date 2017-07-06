package fancy.mycar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
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

	private Tencent mTencent;
	private IUiListener loginListener;
	private String scope;
	private IWXAPI mWXapi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylogin_frm);
		mQueue = Volley.newRequestQueue(this);
		initapi();
		initControl();
		initConfig();
	}

	private void initapi() {
		//qq begin
		mTencent = Tencent.createInstance(Constant.QQ_APPID, MyLogin_FRM.this);
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

		//wx begin
		mWXapi = WXAPIFactory.createWXAPI(this, Constant.WX_APPID, true);
		mWXapi.registerApp(Constant.WX_APPID);
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
			boolean wxlogin = mWXapi.sendReq(req);
			Log.d("wxlogin", "test");
		}catch (Exception ex){
			String err = ex.getMessage();
		}
	}

	private void QQLogin() {
		if (!mTencent.isSessionValid()) {
			mTencent.login(MyLogin_FRM.this, scope, loginListener);
		}
	}

	private void initConfig() {

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
