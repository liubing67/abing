package com.liu.abing.chart;

import android.os.Bundle;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.Tools;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/26 10:55
 * 修改人：Administrator
 * 修改时间：2016/12/26 10:55
 * 修改备注：
 */
public class ChartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initView();
    }
    private void initView()
    {
        findViewById(R.id.but_pieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(ChartActivity.this,null,PieChartActivity.class);
            }
        });
        findViewById(R.id.but_lines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(ChartActivity.this,null,SuitLinesActivity.class);
            }
        });


    }
}
