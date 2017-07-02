package fancy.mycar;

import android.app.Activity;
import android.content.Intent;
import android.net.rtp.AudioCodec;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.videogo.openapi.EZOpenSDK;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import fancy.mycar.R;

public class MyVideo extends Activity {
    private Button TakePhotos;
    private Button ViewPhotos;
    private Button BtnForward, BtnBackward, BtnLeft;

    Button BtnRight;
    Button BtnStop;
    URL videoUrl;
    //    public static String CameraIp;
    public static String CtrlIp;
    public static String CtrlPort;
    //    MySurfaceView r;
    private Socket socket;
    OutputStream socketWriter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.myvideo);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//        r = (MySurfaceView) findViewById(R.id.mySurfaceViewVideo);
        TakePhotos = (Button) findViewById(R.id.TakePhoto);
        ViewPhotos = (Button) findViewById(R.id.ViewPhoto);

        BtnForward = (Button) findViewById(R.id.button_forward);
        BtnBackward = (Button) findViewById(R.id.button_backward);
        BtnLeft = (Button) findViewById(R.id.button_left);
        BtnRight = (Button) findViewById(R.id.button_right);
        BtnStop = (Button) findViewById(R.id.button_stop);

//        if (sdkver != null && sdkver.length >= 3)     {
//            Log.d("rtmpsdk", "rtmp sdk version is:" + sdkver[0] + "." + sdkver[1] + "." + sdkver[2]);
//        }
        //从Intent当中根据key取得value
//        CameraIp = "http://lelelife.vicp.cc:18992/?action=stream";
        CtrlIp = "lelelife.vicp.cc";
        CtrlPort = "19211";


        Log.d("wifirobot", "control is :++++" + CtrlIp);
        Log.d("wifirobot", "CtrlPort is :++++" + CtrlPort);
//        r.GetCameraIP(CameraIp);
        InitSocket();

        BtnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopMove();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rightMove();
                }

                return false;
            }
        });

        BtnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopMove();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    lefeMove();
                }
                return false;
            }
        });
        BtnBackward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopMove();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    backWardMove();
                }
                return false;
            }
        });
        BtnForward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    stopMove();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    forwardMove();
                }
                return false;
            }
        });

        BtnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMove();
            }
        });

        BtnStop.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    confrimStop();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    forwardMove();
                    confrim();
//                    stopMove();
                }
                return false;
            }

        });
        TakePhotos.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (null != Constant.handler) {
                    Message message = new Message();
                    message.what = 1;
                    Constant.handler.sendMessage(message);
                }
            }

        });

        ViewPhotos.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                EZOpenSDK.getInstance().openLoginPage();
            }

        });

    }

    public void InitSocket() {

        try {
            socket = new Socket(InetAddress.getByName(CtrlIp), Integer.parseInt(CtrlPort));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            socketWriter = socket.getOutputStream();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Toast.makeText(this,"初始化网络失败！"+e.getMessage(),Toast.LENGTH_LONG).show();
    }

    public void onDestroy() {
        super.onDestroy();

    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2500) {
                Toast.makeText(getApplicationContext(), "双击退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                stopMove();
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            // TODO Auto-generated method stub
        }
        return super.dispatchKeyEvent(event);
    }


    private void lefeMove() {
        try {
            if (socketWriter == null) {
                InitSocket();
            }

            socketWriter.write(new byte[]{(byte) 0xff, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0xff});
            socketWriter.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void rightMove() {
        try {
            if (socketWriter == null) {
                InitSocket();
            }
            socketWriter.write(new byte[]{(byte) 0xff, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0xff});
            socketWriter.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void backWardMove() {
        try {
            if (socketWriter == null) {
                InitSocket();
            }
            socketWriter.write(new byte[]{(byte) 0xff, (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0xff});
            socketWriter.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void forwardMove() {
        try {
            if (socketWriter == null) {
                InitSocket();
            }
            socketWriter.write(new byte[]{(byte) 0xff, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0xff});
            socketWriter.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void stopMove() {
        try {
            if (socketWriter != null) {
                socketWriter.write(new byte[]{(byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff});
                socketWriter.flush();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void confrim(){
        try {
            if (socketWriter != null) {
                socketWriter.write(new byte[]{(byte) 0xff, (byte) 0xa0, (byte) 0x01, (byte) 0x00, (byte) 0xff});
                socketWriter.flush();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void confrimStop(){
        try {
            if (socketWriter != null) {
                socketWriter.write(new byte[]{(byte) 0xff, (byte) 0xa0, (byte) 0x03, (byte) 0x00, (byte) 0xff});
                socketWriter.flush();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



