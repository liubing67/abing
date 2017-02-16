package com.abing.androidnote.androida笔记.获取短信验证码;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.manstro.parkzuhu.R;
import com.manstro.parkzuhu.tools.code.HttpSend;
import com.manstro.parkzuhu.tools.code.MyCountTimer;
import com.manstro.parkzuhu.tools.code.SMSBroadcastReceiver;
import com.manstro.parkzuhu.tools.code.SMSInteraction;
import com.manstro.parkzuhu.tools.code.UserUtils;

import java.io.UnsupportedEncodingException;

public class RegisterUserActivity extends Activity implements View.OnClickListener,SMSInteraction {

    //控件
    private ImageButton imb_title_back;
    private TextView text_title_title;
    private TextView text_getcode;//获取验证码
    private EditText edit_phone, edit_code;
    private Button button_commit;//下一步

    //数据
    private String TAG="registeruseractivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);//注册用户

        initView();
        regSMSReceiver();
    }

    private void initView() {
        imb_title_back = (ImageButton) findViewById(R.id.imb_title_back);
        text_title_title = (TextView) findViewById(R.id.text_title_title);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_code = (EditText) findViewById(R.id.edit_code);
        text_getcode = (TextView) findViewById(R.id.text_getcode);
        button_commit = (Button) findViewById(R.id.button_commit);
        text_title_title.setText("注册用户");
        imb_title_back.setOnClickListener(this);
        text_getcode.setOnClickListener(this);
        button_commit.setOnClickListener(this);

        //设置电话号码分段显示
        edit_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            private boolean isDelete;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edit_phone.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            isDelete = true;
                        }
                        return false;
                    }
                });
                UserUtils.formatPhoneNumber(s, start, before, count, edit_phone, this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imb_title_back:
                finish();
                break;
            case R.id.text_getcode: //点击获取验证码
                MyCountTimer timeCount = new MyCountTimer(text_getcode, 0xfff30008, 0xff969696);//传入了文字颜色值
                timeCount.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sendinfo();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.button_commit:////            strRes= HttpRequest.sendPost(strRegUrl, strRegParam);

                break;


        }
    }

    /**
     * 注册广播    自动填写验证码                  需要添加权限
     *  <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
     <uses-permission android:name="android.permission.READ_SMS"/>
     */
    private void regSMSReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(Integer.MAX_VALUE);
        SMSBroadcastReceiver smsBR = new SMSBroadcastReceiver();
        registerReceiver(smsBR, filter);
        smsBR.setSMSInteractionListener(RegisterUserActivity.this);
    }
    private void sendinfo() throws UnsupportedEncodingException {
        {
            //以下为所需的参数，测试时请修改,中文请先转为16进制再发送
            String strReg = "101100-WEB-HUAX-107176";   //注册号（由华兴软通提供）
            String strPwd = "QBYAKHYT";                 //密码（由华兴软通提供）
            String strSourceAdd = "";                   //子通道号，可为空（预留参数一般为空）
            String strTim = HttpSend.paraTo16("2012-2-16 12:00:00"); //定时发送时间
            String strPhone = "13225337940";//手机号码，多个手机号用半角逗号分开，最多1000个
            String strContent = HttpSend.paraTo16("您好，您的验证码为：1234，有效5分钟，请在有效期内输入。【消消乐】");       //短信内容

            String strUname = HttpSend.paraTo16("华兴"); //用户名，不可为空
            String strMobile = "13225337940";            //手机号，不可为空
            String strRegPhone = "01065688262";             //座机，不可为空
            String strFax = "01065685318";               //传真，不可为空
            String strEmail = "hxrt@stongnet.com";       //电子邮件，不可为空
            String strPostcode = "100080";               //邮编，不可为空
            String strCompany = HttpSend.paraTo16("华兴软通");    //公司名称，不可为空
            String strAddress = HttpSend.paraTo16("天阳ja");//公司地址，不可为空

            String strNewPwd = "BBBBBBBB";

            //以下参数为服务器URL,以及发到服务器的参数，不用修改
            String strRegUrl = "http://www.stongnet.com/sdkhttp/reg.aspx";
            String strBalanceUrl = "http://www.stongnet.com/sdkhttp/getbalance.aspx";
            String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
            String strSchSmsUrl = "http://www.stongnet.com/sdkhttp/sendschsms.aspx";
            String strStatusUrl = "http://www.stongnet.com/sdkhttp/getmtreport.aspx";
            String strUpPwdUrl = "http://www.stongnet.com/sdkhttp/uptpwd.aspx";
            String strRegParam = "reg=" + strReg + "&pwd=" + strPwd + "&uname=" + strUname + "&mobile=" + strMobile + "&phone=" + strRegPhone + "&fax=" + strFax +
                    "&email=" + strEmail + "&postcode=" + strPostcode +
                    "&company=" + strCompany + "&address=" + strAddress;
            String strBalanceParam = "reg=" + strReg + "&pwd=" + strPwd;
            String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&phone=" + strPhone + "&content=" + strContent;
            String strSchSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&tim=" + strTim + "&phone=" + strPhone + "&content=" + strContent;
            String strStatusParam = "reg=" + strReg + "&pwd=" + strPwd;
            String strUpPwdParam = "reg=" + strReg + "&pwd=" + strPwd + "&newpwd=" + strNewPwd;

            String strRes = new String();

            //以下为HTTP接口主要方法，测试时请打开对应注释进行测试
            //注册
//            strRes = HttpSend.postSend(strRegUrl, strRegParam);


            //查询余额
//            strRes = HttpSend.postSend(strBalanceUrl, strBalanceParam);

            //发送短信
            strRes = HttpSend.postSend(strSmsUrl, strSmsParam);
            Log.e("1111111111111111111111",strRes);
            //定时短信
            //strRes = HttpSend.postSend(strSchSmsUrl, strSchSmsParam);

            //状态报告
            //strRes = HttpSend.postSend(strStatusUrl, strStatusParam);

            //修改密码
            //strRes = HttpSend.postSend(strUpPwdUrl, strUpPwdParam);
        }

    }

    @Override
    public void setCodeValue(String content) {
        if (edit_code != null) {
            edit_code.setText(content);
        }
        Log.e(TAG,content);
    }
}
