package com.abing.androidnote.androida笔记.获取短信验证码.code;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.manstro.park.R;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class MyCountTimer extends CountDownTimer { //验证码时间倒计时
    public static final int TIME_COUNT = 121000;//时间防止从119s开始显示（以倒计时120s为例子）
    private TextView btn;
    private int endStrRid;
    private int normalColor, timingColor;//未计时的文字颜色，计时期间的文字颜色

    /**
     * 参数 millisInFuture         倒计时总时间（如60S，120s等）
     * 参数 countDownInterval    渐变时间（每次倒计1s）

     * 参数 btn               点击的按钮(因为Button是TextView子类，为了通用我的参数设置为TextView）

     * 参数 endStrRid   倒计时结束后，按钮对应显示的文字
     */
    public MyCountTimer (long millisInFuture, long countDownInterval, TextView btn, int endStrRid) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }


    /**

     *参数上面有注释
     */
    public  MyCountTimer (TextView btn, int endStrRid) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = endStrRid;
    }

    public MyCountTimer (TextView btn) {
        super(TIME_COUNT, 1000);
        this.btn = btn;
        this.endStrRid = R.string.txt_getMsgCode_validate;////<string name="txt_getMsgCode_validate">重新获取验证码</string>
    }


    public MyCountTimer (TextView tv_varify, int normalColor, int timingColor) {
        this(tv_varify);
        this.normalColor = normalColor;
        this.timingColor = timingColor;
    }

    // 计时完毕时触发
    @Override
    public void onFinish() {
        if(normalColor > 0){
            btn.setTextColor(normalColor);
        }
        btn.setText(endStrRid);
        btn.setEnabled(true);
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        if(timingColor > 0){
            btn.setTextColor(timingColor);
        }
        btn.setEnabled(false);
        btn.setText(millisUntilFinished / 1000 + "s");
    }


    /*
    *
    * 然后在你要实现倒计时的页面用就可以了：

        比如在AcvitityA中点击倒时间的按钮

        Button smsBtn=findViewById(R.id.....);

        MyCountTimertimeCount = new MyCountTimer(smsBtn, 0xfff30008, 0xff969696);//传入了文字颜色值
        timeCount.start();
    * */
}