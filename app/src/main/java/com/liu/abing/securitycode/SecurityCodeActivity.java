package com.liu.abing.securitycode;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.abing.R;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/3/28 9:57
 * 修改人：Administrator
 * 修改时间：2017/3/28 9:57
 * 修改备注：
 */
public class SecurityCodeActivity extends AppCompatActivity implements SecurityCodeView.InputCompleteListener {

    private SecurityCodeView editText;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securitycode);

        findViews();
        setListener();
    }

    private void setListener() {
        editText.setInputCompleteListener(this);
    }

    private void findViews() {
        editText = (SecurityCodeView) findViewById(R.id.scv_edittext);
        text = (TextView) findViewById(R.id.tv_text);
    }

    @Override
    public void inputComplete() {
        Toast.makeText(getApplicationContext(), "验证码是：" + editText.getEditContent(), Toast.LENGTH_LONG).show();
        if (!editText.getEditContent().equals("1234")) {
            text.setText("验证码输入错误");
            text.setTextColor(Color.RED);
        }
    }

    @Override
    public void deleteContent(boolean isDelete) {
        if (isDelete){
            text.setText("输入验证码表示同意《用户协议》");
            text.setTextColor(Color.BLACK);
        }
    }
}
