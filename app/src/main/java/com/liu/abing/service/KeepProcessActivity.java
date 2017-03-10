package com.liu.abing.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/3/10 12:00
 * 修改人：Administrator
 * 修改时间：2017/3/10 12:00
 * 修改备注：
 */
public class KeepProcessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keepprocess);

        findViewById(R.id.but_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KeepProcessActivity.this,TestService.class);
                startService(intent);
            }
        });
        findViewById(R.id.but_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KeepProcessActivity.this,TestService.class);
                stopService(intent);
            }
        });
    }
}
