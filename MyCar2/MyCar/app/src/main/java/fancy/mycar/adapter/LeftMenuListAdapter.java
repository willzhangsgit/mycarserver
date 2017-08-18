/* 
 * @ProjectName VideoGoJar
 * @Copyright HangZhou Hikvision System Technology Co.,Ltd. All Right Reserved
 * 
 * @FileName CameraListAdapter.java
 * @Description 这里对文件进行描述
 * 
 * @author chenxingyf1
 * @data 2014-7-14
 * 
 * @note 这里写本文件的详细功能描述和注释
 * @note 历史记录
 * 
 * @warning 这里写本文件的相关警告
 */
package fancy.mycar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import fancy.mycar.R;


/**
 * 左侧菜单列表适配器
 * @author chenxingyf1
 * @data 2014-7-14
 */
public class LeftMenuListAdapter extends BaseAdapter {
    private static final String TAG = "LeftMenuListAdapter";
    private List<Map<String, String>> dataList;
    private Context mContext = null;
    /** 监听对象 */
    private View.OnClickListener mListener;
    private ExecutorService mExecutorService = null;// 线程池

    public LeftMenuListAdapter(Context mContext) {
        this.mContext = mContext;

        dataList = new ArrayList<Map<String, String>>();

        dataList.add(new HashMap<String, String>() {{
            put("NAME",  "账户余额");
            put("NUMBER", "1");
        }});
        dataList.add(new HashMap<String, String>() {{
            put("NAME",  "账户充值");
            put("NUMBER", "2");
        }});
        dataList.add(new HashMap<String, String>() {{
            put("NAME",  "奖品列表");
            put("NUMBER", "3");
        }});

    }

    @Override
    public int getCount() {
        return null == dataList ? 0 : dataList.size();
    }

    @Override
    public Map<String, String> getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.left_menu__item1, null);
        Map<String, String> entry = getItem(position);

        final TextView tvMenuItem = (TextView) convertView.findViewById(R.id.left_menu_tv);
        tvMenuItem.setText(entry.get("NAME"));
        final FrameLayout flMenuItem = (FrameLayout) convertView.findViewById(R.id.left_menu_fl);

        flMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, tvMenuItem.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
