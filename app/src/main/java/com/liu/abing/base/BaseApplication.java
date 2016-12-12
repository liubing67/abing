package com.liu.abing.base;

import android.app.Activity;
import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import org.xutils.x;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/30.
 */
public class BaseApplication extends Application {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static BaseApplication instance;
    private String TAG="ABING";
    // 单例模式中获取唯一的ExitApplication 实例
    public static BaseApplication getInstance() {
        if (null == instance) {
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        NoHttp.initialize(this);
        //开启调试模式
        Logger.setDebug(true);
        Logger.setTag("noHttpSample");



        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志


        //打印日志
        com.orhanobut.logger.Logger.init(TAG).logLevel(LogLevel.FULL);
    }

    //将所有的activity添加到集合中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //应用退出时
    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    private static Map<String, Activity> destoryMap = new HashMap<>();

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(String activityName, Activity activity) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
        }
    }
}
