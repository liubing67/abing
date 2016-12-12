package com.tools.http;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.liu.abing.R;
import com.tools.util.ToastUtil;
import com.tools.views.dialog.LoadingDialogView;
import com.tools.views.dialog.WebDialog;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ParseError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;
import com.yolanda.nohttp.tools.HeaderParser;

import java.net.ProtocolException;

/**
 * 项目名称：SecondPay
 * 类描述：结果回调对象
 * 创建人：Liangtao
 * 创建时间：2016/7/30 19:33
 * 修改人：Administrator
 * 修改时间：2016/7/30 19:33
 * 修改备注：
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {

    private Activity mActivity;
    /**
     * Dialog.
     */
    private LoadingDialogView mWaitDialog;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private HttpListener<T> callback;

    /**
     * @param activity     activity用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Activity activity, Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this.mActivity = activity;
        this.mRequest = request;

        if (activity != null && isLoading) {
            mWaitDialog =LoadingDialogView.loadingDefaultDialog(activity,canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                    Log.e("222222222222","222222222222");
                }
            });
        }
        this.callback = httpCallback;
    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (mWaitDialog != null && !mActivity.isFinishing() && !mWaitDialog.isShowing())
        {
            mWaitDialog.show();
        }
    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        if (mWaitDialog != null && mWaitDialog.isShowing())
        {
            mWaitDialog.cancel();
        }
    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        int responseCode = response.getHeaders().getResponseCode();
        if (responseCode > 400 && mActivity != null) {
            if (responseCode == 405) {// 405表示服务器不支持这种请求方法，比如GET、POST、TRACE中的TRACE就很少有服务器支持。
                showMessageDialog(mActivity,mActivity.getText(R.string.request_succeed), mActivity.getText(R.string.request_method_not_allow));
            } else {// 但是其它400+的响应码服务器一般会有流输出。
                showWebDialog(response,mActivity);
            }
        }
        if (callback != null) {
            callback.onSucceed(what, response);
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        if (exception instanceof NetworkError) {// 网络不好
            ToastUtil.customShow(mActivity, R.string.error_please_check_network);
        } else if (exception instanceof TimeoutError) {// 请求超时
            ToastUtil.customShow(mActivity, R.string.error_timeout);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            ToastUtil.customShow(mActivity, R.string.error_not_found_server);
        } else if (exception instanceof URLError) {// URL是错的
            ToastUtil.customShow(mActivity, R.string.error_url_error);
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            ToastUtil.customShow(mActivity, R.string.error_not_found_cache);
        } else if (exception instanceof ProtocolException) {
            ToastUtil.customShow(mActivity, R.string.error_system_unsupport_method);
        } else if (exception instanceof ParseError) {
            ToastUtil.customShow(mActivity, R.string.error_parse_data_error);
        } else {
            ToastUtil.customShow(mActivity, R.string.error_unknow);
        }
        Logger.e("错误：" + exception.getMessage());
        if (callback != null)
            callback.onFailed(what, url, tag, exception, responseCode, networkMillis);
    }

    /**
     * 显示一个WebDialog。
     *
     * @param response 响应。
     */
    public void showWebDialog(Response<?> response,Context context) {
        String result = StringRequest.parseResponseString(response.getHeaders(), response.getByteArray());
        WebDialog webDialog = new WebDialog(context);
        String contentType = response.getHeaders().getContentType();
        webDialog.loadUrl(result, contentType, HeaderParser.parseHeadValue(contentType, "charset", "utf-8"));
        webDialog.show();
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(Context context,CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.know, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
