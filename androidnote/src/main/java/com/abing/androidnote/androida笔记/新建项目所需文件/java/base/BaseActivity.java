package com.abing.androidnote.androida笔记.新建项目所需文件.java.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.manstro.park.ApplicationListActivity;
import com.manstro.park.R;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
    }

        @Override
    protected void onResume() {
        super.onResume();

            //SCREEN_ORIENTATION_PORTRAIT设置手机屏幕竖屏显示    SCREEN_ORIENTATION_LANDSCAPE设置手机横屏显示
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
