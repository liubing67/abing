package com.liu.abing;

import android.os.Bundle;

import com.liu.abing.base.BaseActivity;
import com.tools.views.EasyFlipView;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/9 16:20
 * 修改人：Administrator
 * 修改时间：2017/1/9 16:20
 * 修改备注：
 */
public class EasyFlipActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyflip);

        EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyFlipView);
        easyFlipView.setFlipDuration(1000);
        easyFlipView.setFlipEnabled(true);
        easyFlipView.setFlipOnTouch(true);

    }
}
