package fancy.mycar;

import android.app.Application;

import com.videogo.openapi.EZOpenSDK;

/**
 * Created by Will on 2017/5/17.
 */

public class EzvizApplication extends Application {

	public static String AppKey = "d1c385cf66784cab907c8d131d53007b";

	public static EZOpenSDK getOpenSDK() {
		return EZOpenSDK.getInstance();
	}


	@Override
	public void onCreate() {
		initSDK();
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
}
