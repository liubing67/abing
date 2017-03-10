package com.liu.abing.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/3/10 11:52
 * 修改人：Administrator
 * 修改时间：2017/3/10 11:52
 * 修改备注：
 */
public class TestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("TestService1vvvvvvvvvvvv");
        if (intent.getAction().equals("com.liubing.destroy")) {
            //在这里写重新启动service的相关操作
            Logger.d("TestService1vvvvvvvvvvvvvvvvvv");
            Intent i1 = new Intent(context,TestService.class);
            context.startService(i1);
        }

    }
}
