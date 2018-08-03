package com.liu.abing.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.tools.http.nohttp.CallServer;
import com.tools.http.nohttp.HttpListener;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RedirectHandler;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/11/7 11:02
 * 修改人：Administrator
 * 修改时间：2016/11/7 11:02
 * 修改备注：
 */
public class BaseActivity extends AppCompatActivity {

    protected String TAG;
    /**
            * 记录当前最顶部的Activity
     */
    private static BaseActivity sTopActivity = null;
    /**
     * 请求的队列
     */
//    protected RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
        TAG = this.getClass().getSimpleName();
        BaseApplication.getInstance().addFinishActivity(TAG,BaseActivity.this);
//        requestQueue = NoHttp.newRequestQueue();
        sTopActivity = this; // 确保最新一个Activity创建后，TopActivity立马指向它，此时一些操作可以在onCreate函数执行
    }



    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        request.setRedirectHandler(new RedirectHandler() {
            @Override
            public Request<?> onRedirect(Headers responseHeaders) {
                // 允许重定向时这个方法会被调用
                // 1. 返回null，NoHttp会自动拷贝父请求的请求方法和代理自动请求，不会拷贝其他属性。
                // 2. 返回非null，会把这个新请求的数据交给父请求去解析。
                Request<String> redirectRequest = NoHttp.createStringRequest("http://218.80.1.67:8580"+responseHeaders.getLocation());
                redirectRequest.setRedirectHandler(this);// 为了防止嵌套重定向，这里可以每个子级请求都监听
                Logger.d(responseHeaders.getLocation());
                return redirectRequest;
            }

            @Override
            public boolean isDisallowedRedirect(Headers responseHeaders) {
                // 返回false表示允许重定向
                return false;
            }
        });
        CallServer.getRequestInstance().add(this, what, request, callback, canCancel, isLoading);
    }

    @Override
    protected void onDestroy() {
        CallServer.getRequestInstance().cancelBySign(this);
        super.onDestroy();
    }

    public static Handler getUIHandler() {
        Handler handler = sTopActivity.getWindow().getDecorView().getHandler();
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }
}
