package com.liu.abing.drag;

import android.os.Bundle;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.views.draglayout.DragLayout;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/26 14:35
 * 修改人：Administrator
 * 修改时间：2016/12/26 14:35
 * 修改备注：
 */
public class DragLOActivity extends BaseActivity {

    private DragLayout mDragLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragtwo);

        initView();
    }
    private void initView()
    {
        mDragLayout = (DragLayout) findViewById(R.id.dl);
    }
}
