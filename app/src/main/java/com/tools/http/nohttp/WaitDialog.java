/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tools.http.nohttp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.abing.R;

/**
 * Created in Oct 23, 2015 1:19:04 PM.
 *
 * @author Yan Zhenjie.
 */
public class WaitDialog extends Dialog {

    private static WaitDialog loadingdialog;
    private Context context;
    public WaitDialog(Context context) {
        super(context);
        this.context = context;

    }

    public WaitDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }



    /**
     * 当窗口焦点改变时调用
     */
    public void onWindowFocusChanged(boolean hasFocus) {

        if(loadingdialog == null) {
            return;
        }
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        //使用ImageView显示动画
//        //图片旋转
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_1_anim);
//        imageView.setImageResource(R.drawable.loading_0);
//        imageView.startAnimation(animation);

        //循环显示图片
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    /**
     * 给Dialog设置提示信息
     *
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    /**
     * 弹出自定义ProgressDialog
     *
     * @param message        提示
     * @param cancelable     是否按返回键取消
    //     * @param cancelListener 按下返回键监听
     * @return
     */
    public static WaitDialog loadingDialog(Context context,CharSequence message, boolean cancelable) {
        loadingdialog= new WaitDialog(context, R.style.Loading_Dialog);
        loadingdialog.setTitle("");
        loadingdialog.setContentView(R.layout.dialog_loading);
        TextView txt = (TextView) loadingdialog.findViewById(R.id.message);
        ImageView img = (ImageView) loadingdialog.findViewById(R.id.spinnerImageView);
        if (message == null || message.length() == 0) {
            txt.setVisibility(View.GONE);
//            img.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
        } else {
            txt.setText(message);
//            img.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
        }
        // 按返回键是否取消
        loadingdialog.setCancelable(cancelable);
        //点击view是否消失
        loadingdialog.setCanceledOnTouchOutside(false);
//        // 监听返回键处理
//        loadingdialog.setOnCancelListener(cancelListener);
        // 设置居中
        loadingdialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = loadingdialog.getWindow().getAttributes();
        // 设置背景层透明度
        lp.alpha = 0.8f;
        // 设置背景层黑暗度
        lp.dimAmount = 0.2f;
        loadingdialog.getWindow().setAttributes(lp);
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//        loadingdialog.show();
        return loadingdialog;
    }

    //进度条：加载数据
    public static WaitDialog loadingDialog(Context context) {
        return loadingDialog( context,"", true);
    }

    //进度条：加载数据
    public static WaitDialog loadingDialog(Context context, String msg) {
        return loadingDialog(context, msg, true);
    }

    //进度条：加载数据
    public static WaitDialog loadingDefaultDialog(Context context,boolean cancelable) {
        return loadingDialog(context,"加载中...", cancelable);
    }

}
