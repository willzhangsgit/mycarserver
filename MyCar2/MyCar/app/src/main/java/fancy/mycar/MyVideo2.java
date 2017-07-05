package fancy.mycar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.videogo.constant.IntentConsts;
import com.videogo.errorlayer.ErrorInfo;
import com.videogo.exception.BaseException;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EZOpenSDKListener;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZDeviceInfo;
import com.videogo.openapi.bean.EZLeaveMessage;
import com.videogo.util.ConnectionDetector;
import com.videogo.util.LogUtil;
import com.videogo.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fancy.mycar.bo.ets_cis_config;
import fancy.mycar.ui.cameralist.EZCameraListAdapter;
import fancy.mycar.ui.cameralist.SelectCameraDialog;
import fancy.mycar.ui.realplay.EZRealPlayActivity;
import fancy.mycar.ui.util.ActivityUtils;
import fancy.mycar.ui.util.EZUtils;
import fancy.mycar.ui.util.HttpUtil;
import fancy.mycar.widget.PullToRefreshFooter;
import fancy.mycar.widget.PullToRefreshHeader;
import fancy.mycar.widget.WaitDialog;
import fancy.mycar.widget.pulltorefresh.IPullToRefresh;
import fancy.mycar.widget.pulltorefresh.LoadingLayout;
import fancy.mycar.widget.pulltorefresh.PullToRefreshBase;
import fancy.mycar.widget.pulltorefresh.PullToRefreshListView;

/**
 * Created by Will on 2017/5/23.
 */

public class MyVideo2 extends Activity implements View.OnClickListener, SelectCameraDialog.CameraItemClick {
	protected static final String TAG = "CameraListActivity";
	public final static int REQUEST_CODE = 100;
	public final static int RESULT_CODE = 101;
	/**
	 * 删除设备
	 */
	private final static int SHOW_DIALOG_DEL_DEVICE = 1;

	//private EzvizAPI mEzvizAPI = null;
	private BroadcastReceiver mReceiver = null;

	private PullToRefreshListView mListView = null;
	private View mNoMoreView;
	private EZCameraListAdapter mAdapter = null;

	private LinearLayout mNoCameraTipLy = null;
	private LinearLayout mGetCameraFailTipLy = null;
	private TextView mCameraFailTipTv = null;
	private Button mAddBtn;
	private Button mUserBtn;
	private TextView mMyDevice;
	private TextView mShareDevice;
	private Button mPushCheckBtn;

	private boolean bIsFromSetting = false;

	public final static int TAG_CLICK_PLAY = 1;
	public final static int TAG_CLICK_REMOTE_PLAY_BACK = 2;
	public final static int TAG_CLICK_SET_DEVICE = 3;
	public final static int TAG_CLICK_ALARM_LIST = 4;

	private int mClickType;

	private final static int LOAD_MY_DEVICE = 0;
	private final static int LOAD_SHARE_DEVICE = 1;
	private int mLoadType = LOAD_MY_DEVICE;

	private String mTocken = "";

	public RequestQueue mQueue;
	public List<ets_cis_config> lconfigs = new ArrayList<ets_cis_config>();

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			LogUtil.d(TAG, "handleMessage " + msg);
			/***获取下载的留言ID***/
			String mId;
			switch (msg.what) {
				case 301:
					// TODO: 2016/10/13   下载留言失败
					mId = (String) msg.obj;
					break;
				case 302:
					// TODO: 2016/10/13  下载留言成功
					/*** 获取下载成功的留言ID ***/
					mId = (String) msg.obj;
					break;
				default:
					break;
			}
		}
	};

	@Override
	public void onCameraItemClick(EZDeviceInfo deviceInfo, int camera_index) {
		EZCameraInfo cameraInfo = null;
		Intent intent = null;
		switch (mClickType) {
			case TAG_CLICK_PLAY:
				cameraInfo = EZUtils.getCameraInfoFromDevice(deviceInfo, camera_index);
				if (cameraInfo == null) {
					return;
				}
				break;
			case TAG_CLICK_REMOTE_PLAY_BACK:
				break;
			default:
				break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myvideo2);
		mQueue = Volley.newRequestQueue(this);
		initData();
		initView();
		new Thread(tockeninitTask).start();
		Utils.clearAllNotification(this);

		testServer();
	}

	private void testServer() {
		String servname = "http://10.111.11.34:8091/MyCarServer1/user/getAllUser";
//		String servname = "http://172.18.66.27:8080/NS/servlet/ScheServlet";
		JsonRequest request = new JsonObjectRequest(servname, new JSONObject(), new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				Log.v("data...", jsonObject.toString());
				try{
					String result = jsonObject.getString("resultCode");
					if(result.equals("1")){
						JSONArray realData = jsonObject.getJSONObject("data").getJSONArray("userList");
						lconfigs = new Gson().fromJson(realData.toString(), new TypeToken<List<ets_cis_config>>(){}.getType());
						Log.i("data...",lconfigs.toString());

					}
				}catch (JSONException e){
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Log.v("data...","");
			}
		});

		mQueue.add(request);

//		try {
//			StringRequest stringRequest = new StringRequest("http://10.111.11.34:8091/MyCarServer1/user/getAllUser",
//					new Response.Listener<String>() {
//						@Override
//						public void onResponse(String response) {
//							Log.d("TAG", "gw test it");
//						}
//					}, new Response.ErrorListener() {
//				@Override
//				public void onErrorResponse(VolleyError error) {
//					Log.e("TAG", error.getMessage(), error);
//				}
//			});
//
//			mQueue.add(stringRequest);
//		}catch (Exception ex){
//			String exerr = ex.getMessage();
//		}
	}

	private void initTocken() {
		mTocken = goLogin();
		try {
			if (mTocken != "") {
				EzvizApplication.getOpenSDK().setAccessToken(mTocken);
				refreshButtonClicked();
			}
		}catch(Exception ex){
			String strex = ex.getMessage().toString();
		}
	}

	private String goLogin() {
		String atocken = "";
		try {
			String servname = "https://open.ys7.com/api/lapp/token/get";
			Map<String, String> params = new HashMap<String, String>();
			params.put("appKey", "d1c385cf66784cab907c8d131d53007b");
			params.put("appSecret", "1c90edb6a9c1b66aff2c959909cd7b6e");
			String ntocken = HttpUtil.submitPostData(servname, params,"utf-8");
			Log.d("ntocken",ntocken.toString());
			Map<String, Object> tockenInfo = parseData(ntocken.toString());
			Log.d("tockinfo",tockenInfo.toString());
			atocken = tockenInfo.get("accessToken").toString();
			Log.d("atocken",tockenInfo.toString());

		}
		catch(Exception ex){
			String strex = ex.getMessage().toString();
		}
		return atocken;
	}

	Runnable tockeninitTask = new Runnable() {
		@Override
		public void run() {
			initTocken();
		}
	};

	private void initView() {
		mMyDevice = (TextView) findViewById(R.id.text_my);
		mUserBtn = (Button) findViewById(R.id.btn_user);
		mUserBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popLogoutDialog();
			}
		});

//        mAddBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EZCameraListActivity.this, CaptureActivity.class);
//                startActivity(intent);
//            }
//        });

//        mShareDevice.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mShareDevice.setTextColor(getResources().getColor(R.color.orange_text));
//                mMyDevice.setTextColor(getResources().getColor(R.color.black_text));
//                mAdapter.clearAll();
//                mLoadType = LOAD_SHARE_DEVICE;
//                getCameraInfoList(true);
//            }
//        });

		mMyDevice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMyDevice.setTextColor(getResources().getColor(R.color.orange_text));
				mAdapter.clearAll();
				mLoadType = LOAD_MY_DEVICE;
				getCameraInfoList(true);
			}
		});
		mNoMoreView = getLayoutInflater().inflate(R.layout.no_device_more_footer, null);
		mAdapter = new EZCameraListAdapter(this);
		mAdapter.setOnClickListener(new EZCameraListAdapter.OnClickListener(){

			@Override
			public void onPlayClick(BaseAdapter adapter, View view, int position) {
				mClickType = TAG_CLICK_PLAY;
				final EZDeviceInfo deviceInfo = mAdapter.getItem(position);
				if (deviceInfo.getCameraNum() <= 0 || deviceInfo.getCameraInfoList() == null || deviceInfo.getCameraInfoList().size() <= 0) {
					LogUtil.d(TAG, "cameralist is null or cameralist size is 0");
					return;
				}
				if (deviceInfo.getCameraNum() == 1 && deviceInfo.getCameraInfoList() != null && deviceInfo.getCameraInfoList().size() == 1) {
					LogUtil.d(TAG, "cameralist have one camera");
					final EZCameraInfo cameraInfo = EZUtils.getCameraInfoFromDevice(deviceInfo, 0);
					if (cameraInfo == null) {
						return;
					}

					Intent intent = new Intent(MyVideo2.this, EZRealPlayActivity.class);
					intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, cameraInfo);
					intent.putExtra(IntentConsts.EXTRA_DEVICE_INFO, deviceInfo);
					startActivityForResult(intent, REQUEST_CODE);
					return;
				}
				SelectCameraDialog selectCameraDialog = new SelectCameraDialog();
				selectCameraDialog.setEZDeviceInfo(deviceInfo);
				selectCameraDialog.setCameraItemClick(MyVideo2.this);
				selectCameraDialog.show(getFragmentManager(), "onPlayClick");
			}

			@Override
			public void onDeleteClick(BaseAdapter adapter, View view, int position) {
				showDialog(SHOW_DIALOG_DEL_DEVICE);
			}

			@Override
			public void onAlarmListClick(BaseAdapter adapter, View view, int position) {

			}

			@Override
			public void onRemotePlayBackClick(BaseAdapter adapter, View view, int position) {

			}

			@Override
			public void onSetDeviceClick(BaseAdapter adapter, View view, int position) {

			}

			@Override
			public void onDevicePictureClick(BaseAdapter adapter, View view, int position) {

			}

			@Override
			public void onDeviceVideoClick(BaseAdapter adapter, View view, int position) {

			}

			@Override
			public void onDeviceDefenceClick(BaseAdapter adapter, View view, int position) {

			}
		});

		mListView = (PullToRefreshListView) findViewById(R.id.camera_listview);
		mListView.setLoadingLayoutCreator(new PullToRefreshBase.LoadingLayoutCreator() {

			@Override
			public LoadingLayout create(Context context, boolean headerOrFooter, PullToRefreshBase.Orientation orientation) {
				if (headerOrFooter)
					return new PullToRefreshHeader(context);
				else
					return new PullToRefreshFooter(context, PullToRefreshFooter.Style.EMPTY_NO_MORE);
			}
		});
		mListView.setMode(IPullToRefresh.Mode.BOTH);
		mListView.setOnRefreshListener(new IPullToRefresh.OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView, boolean headerOrFooter) {
				getCameraInfoList(headerOrFooter);
			}
		});
		mListView.getRefreshableView().addFooterView(mNoMoreView);
		mListView.setAdapter(mAdapter);
		mListView.getRefreshableView().removeFooterView(mNoMoreView);

		mNoCameraTipLy = (LinearLayout) findViewById(R.id.no_camera_tip_ly);
		mGetCameraFailTipLy = (LinearLayout) findViewById(R.id.get_camera_fail_tip_ly);
		mCameraFailTipTv = (TextView) findViewById(R.id.get_camera_list_fail_tv);
	}

	private void initData() {
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				LogUtil.debugLog(TAG, "onReceive:" + action);
				if (action.equals(com.videogo.constant.Constant.ADD_DEVICE_SUCCESS_ACTION)) {
					refreshButtonClicked();
				}
			}
		};
		IntentFilter filter = new IntentFilter();
		filter.addAction(com.videogo.constant.Constant.ADD_DEVICE_SUCCESS_ACTION);
		registerReceiver(mReceiver, filter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (bIsFromSetting || (mAdapter != null && mAdapter.getCount() == 0)) {
			refreshButtonClicked();
			bIsFromSetting = false;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mAdapter != null) {
			mAdapter.shutDownExecutorService();
		}
	}

	/**
	 * 从服务器获取最新事件消息
	 */
	private void getCameraInfoList(boolean headerOrFooter) {
		if (this.isFinishing()) {
			return;
		}
		new MyVideo2.GetCamersInfoListTask(headerOrFooter).execute();
	}

	/**
	 * 获取事件消息任务
	 */
	private class GetCamersInfoListTask extends AsyncTask<Void, Void, List<EZDeviceInfo>> {
		private boolean mHeaderOrFooter;
		private int mErrorCode = 0;

		public GetCamersInfoListTask(boolean headerOrFooter) {
			mHeaderOrFooter = headerOrFooter;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//mListView.setFooterRefreshEnabled(true);
			if (mHeaderOrFooter) {
				mListView.setVisibility(View.VISIBLE);
				mNoCameraTipLy.setVisibility(View.GONE);
				mGetCameraFailTipLy.setVisibility(View.GONE);
			}
			mListView.getRefreshableView().removeFooterView(mNoMoreView);
		}

		@Override
		protected List<EZDeviceInfo> doInBackground(Void... params) {
			if (MyVideo2.this.isFinishing()) {
				return null;
			}
			if (!ConnectionDetector.isNetworkAvailable(MyVideo2.this)) {
				mErrorCode = ErrorCode.ERROR_WEB_NET_EXCEPTION;
				return null;
			}

			try {
				List<EZDeviceInfo> result = null;
				if (mLoadType == LOAD_MY_DEVICE) {
					if (mHeaderOrFooter) {
						result = EzvizApplication.getOpenSDK().getDeviceList(0, 20);
					} else {
						result = EzvizApplication.getOpenSDK().getDeviceList((mAdapter.getCount() / 20) + 1, 20);
					}
				} else if (mLoadType == LOAD_SHARE_DEVICE) {
					if (mHeaderOrFooter) {
						result = EzvizApplication.getOpenSDK().getSharedDeviceList(0, 20);
					} else {
						result = EzvizApplication.getOpenSDK().getSharedDeviceList((mAdapter.getCount() / 20) + 1, 20);
					}
				}

				return result;

			} catch (BaseException e) {
				ErrorInfo errorInfo = (ErrorInfo) e.getObject();
				mErrorCode = errorInfo.errorCode;
				LogUtil.debugLog(TAG, errorInfo.toString());

				return null;
			}
		}

		@Override
		protected void onPostExecute(List<EZDeviceInfo> result) {
			super.onPostExecute(result);
			mListView.onRefreshComplete();
			if (MyVideo2.this.isFinishing()) {
				return;
			}

			if (result != null) {
				if (mHeaderOrFooter) {
					CharSequence dateText = DateFormat.format("yyyy-MM-dd kk:mm:ss", new Date());
					for (LoadingLayout layout : mListView.getLoadingLayoutProxy(true, false).getLayouts()) {
						((PullToRefreshHeader) layout).setLastRefreshTime(":" + dateText);
					}
					mAdapter.clearItem();
				}
				if (mAdapter.getCount() == 0 && result.size() == 0) {
					mListView.setVisibility(View.GONE);
					mNoCameraTipLy.setVisibility(View.VISIBLE);
					mGetCameraFailTipLy.setVisibility(View.GONE);
					mListView.getRefreshableView().removeFooterView(mNoMoreView);
				} else if (result.size() < 10) {
					mListView.setFooterRefreshEnabled(false);
					mListView.getRefreshableView().addFooterView(mNoMoreView);
				} else if (mHeaderOrFooter) {
					mListView.setFooterRefreshEnabled(true);
					mListView.getRefreshableView().removeFooterView(mNoMoreView);
				}
				addCameraList(result);
				mAdapter.notifyDataSetChanged();
			}

			if (mErrorCode != 0) {
				onError(mErrorCode);
			}
		}

		protected void onError(int errorCode) {
			switch (errorCode) {
				case ErrorCode.ERROR_WEB_SESSION_ERROR:
				case ErrorCode.ERROR_WEB_SESSION_EXPIRE:
					ActivityUtils.handleSessionException(MyVideo2.this);
					break;
				default:
					if (mAdapter.getCount() == 0) {
						mListView.setVisibility(View.GONE);
						mNoCameraTipLy.setVisibility(View.GONE);
						mCameraFailTipTv.setText(Utils.getErrorTip(MyVideo2.this, R.string.get_camera_list_fail, errorCode));
						mGetCameraFailTipLy.setVisibility(View.VISIBLE);
					} else {
						Utils.showToast(MyVideo2.this, R.string.get_camera_list_fail, errorCode);
					}
					break;
			}
		}
	}

	private void addCameraList(List<EZDeviceInfo> result) {
		int count = result.size();
		EZDeviceInfo item = null;
		for (int i = 0; i < count; i++) {
			item = result.get(i);
			mAdapter.addItem(item);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mReceiver != null) {
			unregisterReceiver(mReceiver);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.camera_list_refresh_btn:
			case R.id.no_camera_tip_ly:
				refreshButtonClicked();
				break;
			default:
				break;
		}
	}

	/**
	 * 刷新点击
	 */
	private void refreshButtonClicked() {
		mListView.setVisibility(View.VISIBLE);
		mNoCameraTipLy.setVisibility(View.GONE);
		mGetCameraFailTipLy.setVisibility(View.GONE);
		mListView.setMode(IPullToRefresh.Mode.BOTH);
		mListView.setRefreshing();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
			case SHOW_DIALOG_DEL_DEVICE:
				break;
		}
		return dialog;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, R.string.update_exit).setIcon(R.drawable.exit_selector);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		if (dialog != null) {
			removeDialog(id);
			TextView tv = (TextView) dialog.findViewById(android.R.id.message);
			tv.setGravity(Gravity.CENTER);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {// 得到被点击的item的itemId
			case 1:// 对应的ID就是在add方法中所设定的Id
				popLogoutDialog();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 弹出登出对话框
	 *
	 * @see
	 * @since V1.0
	 */
	private void popLogoutDialog() {
		AlertDialog.Builder exitDialog = new AlertDialog.Builder(MyVideo2.this);
		exitDialog.setTitle(R.string.exit);
		exitDialog.setMessage(R.string.exit_tip);
		exitDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				new MyVideo2.LogoutTask().execute();
			}
		});
		exitDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		exitDialog.show();
	}

	private class LogoutTask extends AsyncTask<Void, Void, Void> {
		private Dialog mWaitDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mWaitDialog = new WaitDialog(MyVideo2.this, android.R.style.Theme_Translucent_NoTitleBar);
			mWaitDialog.setCancelable(false);
			mWaitDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			EzvizApplication.getOpenSDK().logout();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mWaitDialog.dismiss();
			ActivityUtils.goToLoginAgain(MyVideo2.this);
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_CODE){
			if (requestCode == REQUEST_CODE){
				String deviceSerial = intent.getStringExtra(IntentConsts.EXTRA_DEVICE_ID);
				int cameraNo = intent.getIntExtra(IntentConsts.EXTRA_CAMERA_NO,-1);
				int videoLevel = intent.getIntExtra("video_level",-1);
				if (TextUtils.isEmpty(deviceSerial)){
					return;
				}
				if (videoLevel == -1 || cameraNo == -1){
					return;
				}
				if (mAdapter.getDeviceInfoList() != null){
					for (EZDeviceInfo deviceInfo:mAdapter.getDeviceInfoList()){
						if (deviceInfo.getDeviceSerial().equals(deviceSerial)){
							if (deviceInfo.getCameraInfoList() != null){
								for (EZCameraInfo cameraInfo:deviceInfo.getCameraInfoList()){
									if (cameraInfo.getCameraNo() == cameraNo){
										cameraInfo.setVideoLevel(videoLevel);
										mAdapter.notifyDataSetChanged();
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 查询语音留言并下载获取到的留言文件, 可以直接播放或者存放文件
	 * 文件名称需要注意防重，重复下载名称需要区分
	 */
	private void downloadLeaveMassage(final String deviceSerial) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Calendar mStartTime = Calendar.getInstance();
				mStartTime.set(Calendar.AM_PM, 0);
				mStartTime.set(mStartTime.get(Calendar.YEAR), mStartTime.get(Calendar.MONTH) - 1,
						mStartTime.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				Calendar mEndTime = Calendar.getInstance();
				mEndTime.set(Calendar.AM_PM, 0);
				mEndTime.set(mEndTime.get(Calendar.YEAR), mEndTime.get(Calendar.MONTH),
						mEndTime.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
				try {
					List<EZLeaveMessage> leaveMessages = EzvizApplication.getOpenSDK().getInstance().getLeaveMessageList(deviceSerial, 0, 20, mStartTime, mEndTime);
					if (leaveMessages == null || leaveMessages.size() <= 0) {
						LogUtil.d(TAG, "no leaveMessage");
					}
					for (EZLeaveMessage ezLeaveMessage : leaveMessages) {
						/***文件名称需要注意防重，重复下载名称需要区别**/
						final File file = new File(Environment.getExternalStorageDirectory(), "EZOpenSDK/LeaveMessage/" + deviceSerial + "-" + ezLeaveMessage.getMsgId());
						File parent = file.getParentFile();
						if (parent == null || !parent.exists() || parent.isFile()) {
							parent.mkdirs();
						}
						EzvizApplication.getOpenSDK().getLeaveMessageData(mHandler, ezLeaveMessage, new EZOpenSDKListener.EZLeaveMessageFlowCallback() {
							@Override
							public void onLeaveMessageFlowCallback(int i, byte[] bytes, int i1, String s) {
								LogUtil.d(TAG, "");
								LogUtil.d(TAG, "bytes" + bytes);
								if (!file.exists() || !file.isFile()) {
									try {
										file.createNewFile();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								FileOutputStream fileOutputStream = null;
								try {
									fileOutputStream = new FileOutputStream(file, false);
									if (fileOutputStream != null) {
										fileOutputStream.write(bytes);
									}
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} finally {
									if (fileOutputStream != null) {
										try {
											fileOutputStream.flush();
											fileOutputStream.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								}
							}
						});
					}
				} catch (BaseException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static Map<String, Object> parseData(String data){
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		JsonObject rData = new JsonParser().parse(data).getAsJsonObject().getAsJsonObject("data");
		Map<String, Object> map = g.fromJson(rData.toString(), new TypeToken<Map<String, Object>>() {}.getType());
		return map;
	}
}
