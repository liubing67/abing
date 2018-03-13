package com.liu.abing;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.liu.abing.base.BaseActivity;
import com.maning.library.MClearEditText;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017-8-30 11:49
 * 修改人：Administrator
 * 修改时间：2017-8-30 11:49
 * 修改备注：
 */
public class CusEditActivity extends BaseActivity {

    private Context mContext;

    private MClearEditText mClearEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusedit);

        mContext = this;


        mClearEditText = (MClearEditText) findViewById(R.id.mClearEditText);

        mClearEditText.setOnClearClickListener(new MClearEditText.OnClearClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(mContext, "点击了清除按钮", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
