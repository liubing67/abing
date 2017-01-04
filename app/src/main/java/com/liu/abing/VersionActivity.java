package com.liu.abing;

import android.os.Bundle;
import android.view.View;

import com.liu.abing.base.BaseActivity;
import com.tools.util.updatemanager.UpdateManager;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/4 11:12
 * 修改人：Administrator
 * 修改时间：2017/1/4 11:12
 * 修改备注：
 */
public class VersionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        initView();
    }
    private void initView()
    {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateManager updateManager=new UpdateManager();
                updateManager.checkAppUpdate(VersionActivity.this,false);
            }
        });
    }
}
