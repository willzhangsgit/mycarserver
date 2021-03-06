package fancy.mycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.connect.UserInfo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import fancy.mycar.bo.UserEnrollment;
import fancy.mycar.bo.ets_cis_config;
import fancy.mycar.ui.util.ActivityUtils;

/**
 * Created by Will on 2017/5/23.
 */

public class MyLogin_FRM extends Activity {
	protected static final String TAG = "MyLogin_FRM";
	public static String mQQScope;
	public static Tencent mTencent;
	public static UserInfo mQQUserInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylogin_frm);
		initControl();

		initConfig();

		initQQApi();
	}

	private void initQQApi() {
		mQQScope = "all";
	}

	private void initConfig() {
		String servname = "http://" + Constant.GwServer + "/MyCarServer1/config/getAllConfig/";
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
		Button btnReg = (Button) this.findViewById(R.id.button4);

		final EditText etPassword = (EditText) this.findViewById(R.id.login_password);
		final EditText etPhoneno = (EditText) this.findViewById(R.id.login_username);

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


		btnReg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), RegisterActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		btnGeneal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				UserEnrollment user = new UserEnrollment();
				user.setPhone(etPhoneno.getText().toString());
				user.setPassword(etPassword.getText().toString());
				doLogin(user);
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
		if(mTencent == null){
			mTencent = Tencent.createInstance(Constant.QQ_APPID, EzvizApplication.getCtx());
		}
		if (!mTencent.isSessionValid()) {
			mTencent.login(MyLogin_FRM.this, mQQScope, loginListener);
			EzvizApplication.mTencent = mTencent;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data_intent) {
		super.onActivityResult(requestCode, resultCode, data_intent);

		if(requestCode == 11101) {
			mTencent.onActivityResultData(requestCode,resultCode,data_intent, loginListener);
		}

		if(1 == resultCode){
			UserEnrollment regUser = (UserEnrollment) data_intent.getSerializableExtra("dataList");
			doLogin(regUser);
		}
	}

	private void doLogin(UserEnrollment regUser)
	{
		try {
			String service = "http://" + Constant.GwServer + "/MyCarServer1/user/login/";

			String nickname = regUser.getAccounts();
			String password = regUser.getPassword();
			String phoneno = regUser.getPhone();
			String localip = ActivityUtils.getlocalip(this);
			JSONObject jsonObject = new JSONObject();
			//jsonObject.put("accounts", nickname);
			jsonObject.put("accounts", phoneno);
			jsonObject.put("phone", phoneno);
			//jsonObject.put("verifyCode", verifycode);
			jsonObject.put("password", password);
			jsonObject.put("lastloginip", localip);

			JsonRequest request = new JsonObjectRequest(service, jsonObject, new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject jsonObject) {
					Log.v("data...", jsonObject.toString());
					try {
						String result = jsonObject.getString("resultCode");
						if (result.equals("1")) {
							//登录成功
							JSONObject realData = jsonObject.getJSONObject("data").getJSONObject("loginUser");
							UserEnrollment rUser = new Gson().fromJson(realData.toString(), UserEnrollment.class);
							rUser.setUsersource("");
							EzvizApplication.loggedUser = rUser;
							Intent intent = new Intent();
							intent.setClass(getApplicationContext(), MyVideo2.class);
							startActivity(intent);

						} else {
							Toast.makeText(MyLogin_FRM.this, "Error:" + jsonObject.getString("resultMessage"), Toast.LENGTH_SHORT).show();
							return;
						}
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError volleyError) {
					volleyError.printStackTrace();
				}
			});

			EzvizApplication.getReqQueue().add(request);

		} catch (Exception ex) {
			Toast.makeText(MyLogin_FRM.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
			return;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	IUiListener loginListener = new BaseUiListener("login");

	private class BaseUiListener implements IUiListener {
		private String scope;

		public BaseUiListener(String scope) {
			this.scope = scope;
		}

		@Override
		public void onComplete(Object response) {
			if (null == response) {
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				return;
			}
			//Util.showResultDialog(MyLogin_FRM.this, response.toString(), "登录成功");
			if(this.scope.equals("login")) {
				doComplete((JSONObject) response);
			}else{
				doGetUserInfo((JSONObject) response);
			}
		}

		private void doGetUserInfo(JSONObject jo) {
			try {
				//Toast.makeText(MyLogin_FRM.this, "获取信息成功", Toast.LENGTH_LONG).show();
				String nickname = jo.getString("nickname");

				EzvizApplication.loggedUser.setAccounts(nickname);
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MyVideo2.class);
				startActivity(intent);
			}catch (Exception ex){

			}
		}

		protected void doComplete(JSONObject jo) {
			try {
				//Toast.makeText(MyLogin_FRM.this, "登录成功", Toast.LENGTH_LONG).show();
				String msg = jo.getString("msg");
				String openID = jo.getString("openid");
				String accessToken = jo.getString("access_token");
				String expires = jo.getString("expires_in");
				String pfkey = jo.getString("pfkey");

				mTencent.setOpenId(openID);
				mTencent.setAccessToken(accessToken, expires);

				UserEnrollment lUser = new UserEnrollment();
				lUser.setPhone("-1");
				lUser.setUsersource("QQ");
				lUser.setOpenid(openID);
				lUser.setPfkey(pfkey);
				EzvizApplication.loggedUser = lUser;

				mQQUserInfo = new UserInfo(MyLogin_FRM.this, mTencent.getQQToken());
				mQQUserInfo.getUserInfo(new BaseUiListener("get_user_simple_info"));
			}catch (Exception ex){
				Log.d("QQLoginException", ex.getMessage());
			}
		}

		@Override
		public void onError(UiError e) {

		}

		@Override
		public void onCancel() {

		}
	}
}
