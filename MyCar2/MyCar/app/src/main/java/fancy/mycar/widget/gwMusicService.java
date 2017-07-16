package fancy.mycar.widget;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import fancy.mycar.R;

/**
 * Created by admin on 2017/7/13.
 */

public class gwMusicService extends Service {
    private MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();

        try{
            mp = new MediaPlayer();
            mp = MediaPlayer.create(gwMusicService.this, R.raw.qh);
            mp.prepare();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try{
                    mp.start();
                } catch (IllegalStateException ie){
                    ie.printStackTrace();
                }
            }
        });

        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                try {
                    mp.release();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                return false;
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
