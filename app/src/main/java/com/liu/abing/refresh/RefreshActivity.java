package com.liu.abing.refresh;

import android.os.Bundle;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.Tools;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/3 19:30
 * 修改人：Administrator
 * 修改时间：2017/1/3 19:30
 * 修改备注：
 */
public class RefreshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        initView();
    }
    private void initView()
    {
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(RefreshActivity.this,null,SwipeRefreshLayoutActivity.class);
            }
        });
        findViewById(R.id.but_onlylistview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(RefreshActivity.this,null,OnlyListViewActivity.class);
            }
        });
        findViewById(R.id.but_Refreshload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(RefreshActivity.this,null,RefreshRecyActivity.class);
            }
        });

    }
}
