package fancy.mycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

import fancy.mycar.bo.UserEnrollment;

/**
 * Created by Will on 2017/5/23.
 */

public class RegisterActivity extends Activity {
	protected static final String TAG = "RegisterActivity";
	String strVerifyCode;
	private Button btnYzm,btnRegister;
	private EditText etPhoneNo,etNickName,etPassword,etAddress,etZipcode,etReceiver,etMailAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myregister);

		initControl();

		inittest();
	}

	private void initControl() {
		btnYzm = (Button) findViewById(R.id.tvget_yzm);
		etPhoneNo = (EditText) findViewById(R.id.etphoneno);
		btnRegister = (Button) findViewById(R.id.btn_register);

		etNickName = (EditText) findViewById(R.id.etnickname);
		etPassword = (EditText) findViewById(R.id.etpassword);
		etAddress = (EditText) findViewById(R.id.address);
		etMailAddress = (EditText) findViewById(R.id.mailaddress);
		etZipcode = (EditText) findViewById(R.id.zipcode);
		etReceiver = (EditText) findViewById(R.id.receiver);

		btnYzm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(etPhoneNo.getText().length()!=11){
					Toast.makeText(RegisterActivity.this, "请指定有效的手机号!", Toast.LENGTH_SHORT).show();
					return;
				}else{
					sendVerifyCode(view);
				}
			}
		});

		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				RegUser();
			}
		});
	}

	private void sendVerifyCode(View v) {
		String servname = "http://" + Constant.GwServer + "/MyCarServer1/user/sendVerifyCode/";
		String phoneno = etPhoneNo.getText().toString();
		v.setEnabled(false);
		btnYzm.setText("获取中");
		servname = servname + phoneno;
		Log.i("sendverify", servname);
		JsonRequest request = new JsonObjectRequest(servname, new JSONObject(), new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				Log.v("data...", jsonObject.toString());
				try {
					String result = jsonObject.getString("resultCode");
					if(result.equals("1")){
						JSONArray realData = jsonObject.getJSONObject("data").getJSONArray("verifyCode");
						new Thread(task).start();
						Toast.makeText(RegisterActivity.this, "已将6位验证码发送至您的手机!", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				volleyError.printStackTrace();
				btnYzm.setEnabled(true);
			}
		});

		EzvizApplication.getReqQueue().add(request);
	}

	int counter = 60;
	private final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (counter != 0) {
				btnYzm.setText(counter + "秒");
			} else {
				btnYzm.setText("获取验证码");
				btnYzm.setEnabled(true);
			}
		}
	};

	private final Runnable task = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (counter > 0) {
				handler.sendEmptyMessage(0);
				counter--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					counter = 0;
					handler.sendEmptyMessage(0);
					e.printStackTrace();
				}
			}
			counter = 60;
			btnYzm.setEnabled(true);
		}
	};

	private void RegUser(){
		try {
			String service = "http://" + Constant.GwServer + "/MyCarServer1/user/reg/";
//			String verifycode = etVerifyCode.getText().toString();
//			if (!verifycode.equals(strVerifyCode)) {
//				Toast.makeText(RegisterActivity.this, "验证码错误!", Toast.LENGTH_SHORT).show();
//				return;
//			}
			btnRegister.setEnabled(false);

			final String nickname = etNickName.getText().toString();
			final String password = etPassword.getText().toString();
			final String phoneno = etPhoneNo.getText().toString();
			String address = etAddress.getText().toString();
			String mailaddress = etMailAddress.getText().toString();
			String zipcode = etZipcode.getText().toString();
			String receiver = etReceiver.getText().toString();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accounts", nickname);
			jsonObject.put("phone", phoneno);
			//jsonObject.put("verifyCode", verifycode);
			jsonObject.put("password", password);
			jsonObject.put("address", address);
			jsonObject.put("mailaddress", mailaddress);
			jsonObject.put("zipcode", zipcode);
			jsonObject.put("receiver", receiver);

			JsonRequest request = new JsonObjectRequest(service, jsonObject, new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject jsonObject) {
					Log.v("data...", jsonObject.toString());
					try {
						String result = jsonObject.getString("resultCode");
						if (result.equals("1")) {
							//注册成功
							String userid = jsonObject.getJSONObject("data").getString("regrtn");
							UserEnrollment regUser = new UserEnrollment();
							regUser.setUserid(Integer.parseInt(userid));
							regUser.setAccounts(nickname);
							regUser.setPassword(password);
							regUser.setPhone(phoneno);

							Intent intent = getIntent();
							intent.putExtra("dataList", (Serializable) regUser);
							setResult(1, intent);
							finish();
						} else {
							Toast.makeText(RegisterActivity.this, jsonObject.getString("resultMessage"), Toast.LENGTH_SHORT).show();
							btnRegister.setEnabled(true);
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
					btnYzm.setEnabled(true);
				}
			});

			EzvizApplication.getReqQueue().add(request);

		} catch (Exception ex) {
			Toast.makeText(RegisterActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
			btnRegister.setEnabled(true);
			return;
		}
	}

	private void inittest() {
		etPhoneNo.setText("13111111111");
		etNickName.setText("willzhang");
		etPassword.setText("111111");
		etAddress.setText("测试地址");
		etMailAddress.setText("报春路");
		etZipcode.setText("201111");
		etReceiver.setText("测试人");
	}
}
