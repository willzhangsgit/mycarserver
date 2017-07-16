package fancy.mycar;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.videogo.openapi.EZOpenSDK;

import org.json.JSONObject;

import java.util.List;

import fancy.mycar.bo.UserEnrollment;
import fancy.mycar.bo.ets_cis_config;

/**
 * Created by Will on 2017/5/17.
 */

public class EzvizApplication extends Application {

	public static String AppKey = "d1c385cf66784cab907c8d131d53007b";

	public static EZOpenSDK getOpenSDK() {
		return EZOpenSDK.getInstance();
	}

	private static Context ctx;
	private static RequestQueue mQueue;
	public static List<ets_cis_config> configs;

	public static Tencent mTencent;
	public static IUiListener loginListener;
	public static String scope;
	public static IWXAPI mWXapi;
	public static UserEnrollment loggedUser;

	@Override
	public void onCreate() {
		//消息总线
		mQueue = Volley.newRequestQueue(getApplicationContext());
		ctx = getApplicationContext();

		initSDK();

		initQQapi();

		initWXapi();
	}

	public static Context getCtx() {
		return ctx;
	}

	public static RequestQueue getReqQueue() {
		return mQueue;
	}

	private void initSDK() {
		/**********国内版本初始化EZOpenSDK**************/
		{
			/**
			 * sdk日志开关，正式发布需要设置未false
			 */
			EZOpenSDK.showSDKLog(false);

			/**
			 * 设置是否支持P2P取流,详见api
			 */
			EZOpenSDK.enableP2P(true);

			/**
			 * APP_KEY请替换成自己申请的
			 */
			EZOpenSDK.initLib(this, AppKey, "");
		}
	}

	private void initQQapi(){
		mTencent = Tencent.createInstance(Constant.QQ_APPID, ctx);
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
						Toast.makeText(ctx, "登录成功",
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

	private void initWXapi(){
		mWXapi = WXAPIFactory.createWXAPI(this, Constant.WX_APPID, true);
		mWXapi.registerApp(Constant.WX_APPID);
	}
}
