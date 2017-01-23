package com.liu.abing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liu.abing.base.BaseActivity;
import com.liu.extend.constant.Urls;
import com.tools.Tools;
import com.tools.http.nohttp.HttpListener;
import com.tools.util.ToastUtil;
import com.tools.views.dialog.LoadingDialogView;
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
 * 创建时间：2016/11/9 9:07
 * 修改人：Administrator
 * 修改时间：2016/11/9 9:07
 * 修改备注：
 */
public class NewsDetailActivity extends BaseActivity {


    private LoadingDialogView dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
//        dialogView= LoadingDialogView.loadingDefaultDialog(NewsDetailActivity.this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
//                dialogView.show();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        dialogView.dismiss();
//                        Log.e("11111111111","11111111111");
//                    }
//                }).start();
            }
        });
//        dialogView.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
////                Log.e("222222222222","222222222222");
//            }
//        });
        Log.e("1111111111111111","11111111111onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e("1111111111111111","11111111111onNewIntent");
    }

    private void doLogin() {
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
            Log.d("1111111111", "loginresult = " + result);
            ToastUtil.showShort(NewsDetailActivity.this,result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                int rtState = jsonObject.optInt("rtState");
                String rtMsrg = jsonObject.optString("rtMsrg");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {

        }
    };


}
