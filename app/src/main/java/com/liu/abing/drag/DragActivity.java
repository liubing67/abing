package com.liu.abing.drag;

import android.os.Bundle;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.Tools;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/26 14:33
 * 修改人：Administrator
 * 修改时间：2016/12/26 14:33
 * 修改备注：
 */
public class DragActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);

        initView();
    }
    private void initView()
    {
        findViewById(R.id.but_drag1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(DragActivity.this,null,DragLActivity.class);
            }
        });
        findViewById(R.id.but_drag2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(DragActivity.this,null,DragLOActivity.class);
            }
        });
        findViewById(R.id.but_drag3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(DragActivity.this,null,DragThreeActivity.class);
            }
        });
    }
}
