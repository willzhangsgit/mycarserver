package fancy.mycar;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.videogo.openapi.EZOpenSDK;

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
	public static IWXAPI mWXapi;
	public static UserEnrollment loggedUser;

	@Override
	public void onCreate() {
		//消息总线
		mQueue = Volley.newRequestQueue(getApplicationContext());
		ctx = getApplicationContext();

		initSDK();

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

	private void initWXapi(){
		mWXapi = WXAPIFactory.createWXAPI(this, Constant.WX_APPID, true);
		mWXapi.registerApp(Constant.WX_APPID);
	}
}
