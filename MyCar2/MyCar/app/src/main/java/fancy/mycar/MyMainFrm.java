package fancy.mycar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyMainFrm extends Activity {

    EditText CameraIP, ControlIP, Port;
    Button Button_go;
    String videoUrl, controlUrl, port;
    public static String CameraIp;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mymainfrm);

        CameraIP = (EditText) findViewById(R.id.editIP);
        ControlIP = (EditText) findViewById(R.id.ip);
        Port = (EditText) findViewById(R.id.port);

        Button_go = (Button) findViewById(R.id.button_go);

        videoUrl = CameraIP.getText().toString();
        controlUrl = ControlIP.getText().toString();
        port = Port.getText().toString();


        videoUrl = "http://lelelife.vicp.cc:18992/?action=stream";
        controlUrl = "lelelife.vicp.cc";
        port = "19211";

        Button_go.requestFocusFromTouch();


        Button_go.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.putExtra("CameraIp", videoUrl);
                intent.putExtra("ControlUrl", controlUrl);
                intent.putExtra("Port", port);

                intent.putExtra("Is_Scale", true);
                intent.setClass(MyMainFrm.this, MyVideo.class);
                MyMainFrm.this.startActivity(intent);
                finish();
                System.exit(0);
            }
        });

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}


