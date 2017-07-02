package fancy.mycar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fancy.mycar.ui.util.HttpUtil;

import static android.R.attr.data;
import static fancy.mycar.Constant.context;


//import com.tencent.rtmp.TXLivePlayer;

public class MainActivity extends AppCompatActivity {

    String ntocken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TXLivePlayer mLivePlayer = new TXLivePlayer(this);    willtest old version
        new Thread(networkTask).start();
    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            goLogin();
        }
    };

    private void goLogin() {
        //EZOpenSDK.getInstance().openLoginPage();
        try {
            String servname = "https://open.ys7.com/api/lapp/token/get";
            Map<String, String> params = new HashMap<String, String>();
            params.put("appKey", "d1c385cf66784cab907c8d131d53007b");
            params.put("appSecret", "1c90edb6a9c1b66aff2c959909cd7b6e");
            ntocken = HttpUtil.submitPostData(servname, params,"utf-8");
            Log.d("ntocken",ntocken.toString());
            Map<String, Object> tockenInfo = parseData(ntocken.toString());
            Log.d("tockinfo",tockenInfo.toString());
            String atocken = tockenInfo.get("accessToken").toString();
            Log.d("atocken",tockenInfo.toString());
        }
        catch(Exception ex){
            String strex = ex.getMessage().toString();
        }
    }

    private static Map<String, Object> parseData(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        JsonObject rData = new JsonParser().parse(data).getAsJsonObject().getAsJsonObject("data");
        Map<String, Object> map = g.fromJson(rData.toString(), new TypeToken<Map<String, Object>>() {}.getType());
        return map;
    }
}
