package com.liu.abing.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.extend.constant.Urls;
import com.orhanobut.logger.Logger;
import com.tools.Tools;
import com.tools.http.nohttp.HttpListener;
import com.tools.http.okhttp.IRequestCallback;
import com.tools.http.okhttp.IRequestManager;
import com.tools.http.okhttp.RequestFactory;
import com.tools.util.ToastUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/27 9:53
 * 修改人：Administrator
 * 修改时间：2016/12/27 9:53
 * 修改备注：
 */
public class NetworkRequestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networkrequest);

        initView();
    }
    private void initView()
    {
        findViewById(R.id.but_nohttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noHttpRequest();
            }
        });
        findViewById(R.id.but_okhttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okHttpRequest();
            }
        });
    }

    private void noHttpRequest()
    {
        Request request = NoHttp.createStringRequest(Urls.LOGIN, RequestMethod.POST);
        request.setConnectTimeout(30000);
        request.add("username", "13225337945");
        request.add("password", "l123456789");
        request.add("sign", Tools.getMD5("13515859857"+"2042386"));
        request(1, request, httpListener, true, true);
    }
    /**
     * 接受响应
     */
    private HttpListener<String> httpListener = new HttpListener<String>() {
        @Override
        public void onSucceed(int what, Response<String> response) {
            // 拿到请求结果
            String result = response.get();
            ToastUtil.customShow(NetworkRequestActivity.this,result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                int rtState = jsonObject.optInt("rtState");
                String rtMsrg = jsonObject.optString("rtMsrg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            Logger.d(TAG, exception.getMessage());
            ToastUtil.customShow(NetworkRequestActivity.this,exception.getMessage());
        }

    };

    private void okHttpRequest()
    {
        IRequestManager iRequestManager=RequestFactory.getRequestManager();
        iRequestManager.post(Urls.LOGIN, "liubing", new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
                Logger.d(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
                Logger.d("1111111111111111");
            }
        });
    }
}
