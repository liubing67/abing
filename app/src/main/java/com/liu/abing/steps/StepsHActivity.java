package com.liu.abing.steps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.steps.step.fragment.StepActivity;
import com.tools.Tools;
import com.tools.views.steps.StepsView;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/22 9:24
 * 修改人：Administrator
 * 修改时间：2016/12/22 9:24
 * 修改备注：
 */
public class StepsHActivity extends Activity {

    private StepsView stepsView;
    private float eventX = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        initView();
    }

    private void initView() {
        stepsView = (StepsView) findViewById(R.id.stepsView);
        stepsView.setTitle(new String[]{"填写邮箱", "验证邮箱", "填写密码", "完善个人信息"});
        stepsView.setPosition(2);

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsView.next();
            }
        });
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsView.reset();
            }
        });
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsView.back();
            }
        });
        findViewById(R.id.but_step).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(StepsHActivity.this,null, StepActivity.class);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指在屏幕上的坐标
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下
                eventX = event.getX();
//                eventY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                break;
            case MotionEvent.ACTION_UP://松开
                if (event.getX() - eventX > 0) {
                    Log.e("sss", "右");
                    stepsView.back();
                } else if (event.getX() - eventX < 0) {
                    Log.e("sss", "左");
                    stepsView.next();
                }
                break;
        }
        return true;
    }
}
