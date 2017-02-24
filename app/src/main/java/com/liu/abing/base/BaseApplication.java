package com.liu.abing.base;

import android.app.Activity;
import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.URLConnectionNetworkExecutor;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

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

        //开启调试模式
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。


        // 一般情况下你只需要这样初始化：
//        NoHttp.initialize(this);
        // 如果你需要自定义配置：
        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒，默认10s。
                .setConnectTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒，默认10s。
                .setReadTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .setCacheStore(
                        new DBCacheStore(this).setEnable(true) // 如果不使用缓存，设置false禁用。
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现。
                .setCookieStore(
                        new DBCookieStore(this).setEnable(false) // 如果不维护cookie，设置false禁用。
                )
                // 配置网络层，默认使用URLConnection，如果想用OkHttp：OkHttpNetworkExecutor。
                .setNetworkExecutor(new OkHttpNetworkExecutor())
        );

        // 如果你需要用OkHttp，请依赖下面的项目，version表示版本号：
        // compile 'com.yanzhenjie.nohttp:okhttp:version'

        // 具体文档请看：https://github.com/yanzhenjie/NoHttp













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

    public Map<String, Activity> destoryMap = new HashMap<>();

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public void addFinishActivity(String activityName, Activity activity) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity  BaseApplication.getInstance().finishActivity("ChartActivity");
     */
    public void finishActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(activityName).finish();
        }
    }
}
