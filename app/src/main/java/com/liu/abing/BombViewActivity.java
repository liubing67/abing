package com.liu.abing;

import android.os.Bundle;

import com.liu.abing.base.BaseActivity;
import com.tools.views.BombView;

/**
 * 测试烟花效果
 */

public class BombViewActivity extends BaseActivity {

    private BombView mBombView;

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            mBombView.startBomb();
//            getUIHandler().postDelayed(task, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_bomb_view);

        mBombView = (BombView) findViewById(R.id.bombview);

        getUIHandler().postDelayed(task, 5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBombView.release();
    }
}
