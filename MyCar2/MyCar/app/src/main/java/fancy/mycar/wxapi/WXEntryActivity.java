package fancy.mycar.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import fancy.mycar.Constant;
import fancy.mycar.EzvizApplication;
import fancy.mycar.MyLogin_FRM;
import fancy.mycar.MyVideo2;
import fancy.mycar.bo.UserEnrollment;
import fancy.mycar.bo.WxUserInfo;
import fancy.mycar.util.Util;

/**
 * Created by admin on 2017/7/21.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    public static WxUserInfo mwUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EzvizApplication.mWXapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.d("WxLogin req", "req 收到回调");
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("WxLogin resp", "resp 收到回调");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.d("WxLogin Cancel", String.valueOf(resp.getType()));
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case 1:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;
                        String wxappsecret = Util.getconfig("wx_appsecret","");
                        if(wxappsecret == null){
                            Toast.makeText(this,"服务器连接失败", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), MyLogin_FRM.class);
                            startActivity(intent);
                        }
                        getWxAccessTocken(code, wxappsecret);

                        break;

                    case 2:
                        Log.d("Wx_OnResp", "分享成功");
                        finish();
                        break;
                }
                break;
        }
    }

    private void getWxAccessTocken(String code, String wxappsecret) {
        Log.d("getWxUserInfo", "获取用户令牌");
        try {
            String service = Constant.WxServer + "/oauth2/access_token";

            JSONObject jsonObject = new JSONObject();
            service += "?appid=" + Constant.WX_APPID
                        +"&secret=" + wxappsecret
                        +"&code=" + code
                        +"&grant_type=authorization_code";

            JsonRequest request = new JsonObjectRequest(service, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.v("data...", jsonObject.toString());
                    try {
                        WxUserInfo wUser = new Gson().fromJson(jsonObject.toString(), WxUserInfo.class);
                        if(wUser.getAccess_token()!=null && wUser.getAccess_token() != ""){
                            mwUser = wUser;
                            getWxUserInfo(wUser.getAccess_token(), wUser.getOpenid());
                        }else{
                            Toast.makeText(WXEntryActivity.this,"获取微信令牌失败，请稍后重试", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), MyLogin_FRM.class);
                            startActivity(intent);
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
            Toast.makeText(WXEntryActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void getWxUserInfo(String at, String openid) {
        Log.d("getWxUserInfo", "获取用户信息");
        try {
            String service = Constant.WxServer + "/userinfo";

            JSONObject jsonObject = new JSONObject();
            service += "?access_token=" + at
                    +"&openid=" + openid;

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, service, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            Log.v("data...", jsonObject.toString());
                            try {
                                WxUserInfo wUser = new Gson().fromJson(jsonObject.toString(), WxUserInfo.class);
                                if(wUser.getUnionid()!=null && wUser.getUnionid() != ""){
                                    String wnickname = new String(wUser.getNickname().getBytes(), "UTF-8");
                                    mwUser.setNickname(wnickname);
                                    mwUser.setHeadimgurl(wUser.getHeadimgurl());

                                    UserEnrollment eUser = new UserEnrollment();
                                    eUser.setPhone("-1");
                                    eUser.setUsersource("WX");
                                    eUser.setOpenid(mwUser.getOpenid());
                                    eUser.setAccounts(mwUser.getNickname());
                                    eUser.setGender(mwUser.getSex());
                                    eUser.setWxuser(mwUser);

                                    EzvizApplication.loggedUser = eUser;

                                    //登录成功
                                    Intent intent = new Intent();
                                    intent.setClass(getApplicationContext(), MyVideo2.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(WXEntryActivity.this,"获取用户信息失败，请稍后重试", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setClass(getApplicationContext(), MyLogin_FRM.class);
                                    startActivity(intent);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.getMessage();
                }

            })
            {
                protected Response<JSONObject>  parseNetworkResponse(NetworkResponse response)
                {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(new String(response.data,"UTF-8"));
                        return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return Response.error(new ParseError(e));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return Response.error(new ParseError(e));
                    }
                }
            };

            EzvizApplication.getReqQueue().add(request);

        } catch (Exception ex) {
            Toast.makeText(WXEntryActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
