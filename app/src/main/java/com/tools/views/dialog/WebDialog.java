package com.tools.views.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 项目名称：SecondPay
 * 类描述：加载网页的弹框
 * 创建人：Liangtao
 * 创建时间：2016/7/31 11:50
 * 修改人：Administrator
 * 修改时间：2016/7/31 11:50
 * 修改备注：
 */
public class WebDialog extends AlertDialog.Builder {

    private AlertDialog alertDialog;

    private WebView webView;

    public WebDialog(Context context) {
        super(context);
        webView = new WebView(context);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    view.loadUrl(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        setView(webView);
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    public void loadUrl(String data, String mimeType, String encoding) {
        webView.loadData(data, mimeType, encoding);
    }

    public void dismiss() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    public void cancel() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.cancel();
    }

    @Override
    public AlertDialog show() {
        if (alertDialog == null) {
            alertDialog = create();
            alertDialog.setTitle(null);
        }
        if (!alertDialog.isShowing())
            alertDialog.show();
        return alertDialog;
    }
}
