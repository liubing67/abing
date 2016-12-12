package com.liu.abing.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.http.CallServer;
import com.tools.http.HttpListener;
import com.yolanda.nohttp.NoHttp;
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
     * 请求的队列
     */
    protected RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
        TAG = this.getClass().getSimpleName();
        requestQueue = NoHttp.newRequestQueue();
    }



    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(this);
        CallServer.getRequestInstance().add(this, what, request, callback, canCancel, isLoading);
    }

    @Override
    protected void onDestroy() {
        CallServer.getRequestInstance().cancelBySign(this);
        super.onDestroy();
    }
}
