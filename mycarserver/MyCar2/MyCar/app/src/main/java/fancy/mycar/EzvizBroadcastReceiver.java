package fancy.mycar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.videogo.constant.*;
import com.videogo.openapi.EzvizAPI;
import com.videogo.openapi.bean.EZAccessToken;
import com.videogo.util.Utils;

/**
 * Created by Will on 2017/5/17.
 */

public class EzvizBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "EzvizBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
			EzvizAPI.getInstance().refreshNetwork();
		} else if (action.equals(com.videogo.constant.Constant.ADD_DEVICE_SUCCESS_ACTION)) {
			String deviceId = intent.getStringExtra(IntentConsts.EXTRA_DEVICE_ID);
			Utils.showToast(context, context.getString(R.string.device_is_added, deviceId));
		} else if (action.equals(com.videogo.constant.Constant.OAUTH_SUCCESS_ACTION)) {
			Intent toIntent = new Intent(context, fancy.mycar.ui.cameralist.EZCameraListActivity.class);
			toIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			/*******   获取登录成功之后的EZAccessToken对象   *****/
			EZAccessToken token = fancy.mycar.EzvizApplication.getOpenSDK().getEZAccessToken();
			context.startActivity(toIntent);
		}
	}
}
