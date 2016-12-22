package com.liu.abing.steps.step.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/22 11:34
 * 修改人：Administrator
 * 修改时间：2016/12/22 11:34
 * 修改备注：
 */
public class StepActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        VerticalStepViewReverseFragment mVerticalStepViewFragment = new VerticalStepViewReverseFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, mVerticalStepViewFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        VerticalStepViewReverseFragment mVerticalStepViewFragment;
        DrawCanvasFragment mDrawCanvasFragment;
        HorizontalStepviewFragment mHorizontalStepviewFragment;
        VerticalStepViewFrowardFragment mVerticalStepViewReverseFragment;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_horizontal_stepview:
                mHorizontalStepviewFragment = new HorizontalStepviewFragment();
                fragmentTransaction.replace(R.id.container, mHorizontalStepviewFragment).commit();
                break;

            case R.id.action_drawcanvas:
                mDrawCanvasFragment = new DrawCanvasFragment();
                fragmentTransaction.replace(R.id.container, mDrawCanvasFragment).commit();
                break;
            case R.id.action_vertical_reverse:
                mVerticalStepViewFragment = new VerticalStepViewReverseFragment();
                fragmentTransaction.replace(R.id.container, mVerticalStepViewFragment).commit();
                break;

            case R.id.action_vertical_forward:
                mVerticalStepViewReverseFragment = new VerticalStepViewFrowardFragment();
                fragmentTransaction.replace(R.id.container, mVerticalStepViewReverseFragment).commit();
                break;

            case R.id.action_test_horizontal_stepview:
                startActivity(new Intent(this, TestHorizontalStepViewActivity.class));
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
