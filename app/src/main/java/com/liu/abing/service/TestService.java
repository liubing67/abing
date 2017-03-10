package com.liu.abing.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 项目名称：abing
 * 类描述：service +broadcast 方式，就是当service走ondestory的时候，发送一个自定义的广播，当收到广播的时候，重新启动service；
 * 创建人：liubing
 * 创建时间：2017/3/10 11:40
 * 修改人：Administrator
 * 修改时间：2017/3/10 11:40
 * 修改备注：
 */
public class TestService extends Service {
    private final String TAG = "TestService1";
    //必须要实现的方法
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用!");
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate方法被调用!");
        super.onCreate();
    }

    //手动返回START_STICKY，亲测当service因内存不足被kill，当内存又有的时候，service又被重新创建，比较不错，但是不能保证任何情况下都被重建，比如进程被干掉了。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_REDELIVER_INTENT;
        Log.i(TAG, "onStartCommand方法被调用!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestory方法被调用!");
        stopForeground(true);
        Intent intent = new Intent("com.liubing.destroy");
        sendBroadcast(intent);
        super.onDestroy();
    }
}
