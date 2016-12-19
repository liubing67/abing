package com.liu.abing.roll;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.views.MarqueeText;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/19 16:30
 * 修改人：Administrator
 * 修改时间：2016/12/19 16:30
 * 修改备注：
 */
public class TextViewRollActivity extends BaseActivity {

    private MarqueeText text_notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textroll);

        initView();
    }
    private void initView()
    {
        text_notify= (MarqueeText) findViewById(R.id.text_notify);
    }
}
