package com.liu.abing.wheel;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

import java.util.ArrayList;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017-6-7 10:38
 * 修改人：Administrator
 * 修改时间：2017-6-7 10:38
 * 修改备注：
 */
public class WheelActivity extends BaseActivity {

    private int hour;
    private int minute;
    private TextView tv;
    private EasyPickerView epvH;
    private EasyPickerView epvM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);

        tv = (TextView) findViewById(R.id.tv);

        // hours
        initHours();

        // minutes
        initMinutes();

        // btns
        initBtns();
    }

    private void initHours() {
        epvH = (EasyPickerView) findViewById(R.id.epv_h);
        final ArrayList<String> hDataList = new ArrayList<>();
        for (int i = 0; i < 24; i++)
            hDataList.add("" + i);

        epvH.setDataList(hDataList);
        epvH.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {
                hour = Integer.parseInt(hDataList.get(curIndex));
                tv.setText(hour + "h" + minute + "m");
            }

            @Override
            public void onScrollFinished(int curIndex) {
                hour = Integer.parseInt(hDataList.get(curIndex));
                tv.setText(hour + "h" + minute + "m");
            }
        });
    }

    private void initMinutes() {
        epvM = (EasyPickerView) findViewById(R.id.epv_m);
        final ArrayList<String> dataList2 = new ArrayList<>();
        for (int i = 0; i < 60; i++)
            dataList2.add("" + i);

        epvM.setDataList(dataList2);
        epvM.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {
                minute = Integer.parseInt(dataList2.get(curIndex));
                tv.setText(hour + "h" + minute + "m");
            }

            @Override
            public void onScrollFinished(int curIndex) {
                minute = Integer.parseInt(dataList2.get(curIndex));
                tv.setText(hour + "h" + minute + "m");
            }
        });
    }

    private void initBtns() {
        // hours
        final EditText et_h = (EditText) findViewById(R.id.et_h);

        Button btnTo = (Button) findViewById(R.id.btn_to_h);
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_h.getText())) {
                    int index = Integer.parseInt(et_h.getText().toString());
                    epvH.moveTo(index);
                }
            }
        });

        Button btnBy = (Button) findViewById(R.id.btn_by_h);
        btnBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_h.getText())) {
                    int index = Integer.parseInt(et_h.getText().toString());
                    epvH.moveBy(index);
                }
            }
        });

        // minutes
        final EditText et_m = (EditText) findViewById(R.id.et_m);

        Button btnTo_m = (Button) findViewById(R.id.btn_to_m);
        btnTo_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_m.getText())) {
                    int index = Integer.parseInt(et_m.getText().toString());
                    epvM.moveTo(index);
                }
            }
        });

        Button btnBy_m = (Button) findViewById(R.id.btn_by_m);
        btnBy_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_m.getText())) {
                    int index = Integer.parseInt(et_m.getText().toString());
                    epvM.moveBy(index);
                }
            }
        });

        findViewById(R.id.btn_other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WheelActivity.this,OtherActivity.class);
                startActivity(intent);
            }
        });
    }
}
