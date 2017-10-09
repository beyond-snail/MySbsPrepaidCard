package com.mysbsprepaidcard.zfsbs.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.mysbsprepaidcard.zfsbs.common.UploadFunc;

import java.util.Date;

/**
 * 流水上送启动一个定时服务
 */

/**
 * 启动定时任务
 * Intent intent = new Intent(this,UploadService.class);
    startService(intent);
 *
 */
public class UploadService extends Service {


    /**
     * 每隔60s执行一次
     */
    private static final int LOAD_TIME = 60*1000;



    public UploadService() {
    }




    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "打印时间: " + new Date().
                        toString());

                UploadFunc.getInstance().isCheckUploadStatus(getApplicationContext());
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int five = LOAD_TIME; // 这是5s
        long triggerAtTime = SystemClock.elapsedRealtime() + five;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
