package com.liu.abing.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.abing.base.BaseApplication;
import com.liu.abing.service.KeepProcessActivity;
import com.liu.abing.service.TestService;
import com.orhanobut.logger.Logger;
import com.tools.util.pictures.HelperMethod;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ArrayList<LinearLayout> lstLayouts;
    private HashMap<Integer, Fragment> mapFragments;
    private HashMap<Integer, ImageView> mapImageViews;
    private HashMap<Integer, TextView> mapTextViews;
    private int currentTabID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化界面信息
        initView();
    }

    //初始化界面信息
    private void initView() {
        //界面
        lstLayouts = new ArrayList<LinearLayout>();
        lstLayouts.add((LinearLayout) findViewById(R.id.btn_layout1));
        lstLayouts.add((LinearLayout) findViewById(R.id.btn_layout2));
        lstLayouts.add((LinearLayout) findViewById(R.id.btn_layout3));
        lstLayouts.add((LinearLayout) findViewById(R.id.btn_layout4));
        mapFragments = new HashMap<Integer, Fragment>();
        mapFragments.put(R.id.btn_layout1, new Fragment1());
        mapFragments.put(R.id.btn_layout2, new Fragment2());
        mapFragments.put(R.id.btn_layout3, new Fragment3());
        mapFragments.put(R.id.btn_layout4, new Fragment4());
        mapImageViews = new HashMap<Integer, ImageView>();
        mapImageViews.put(R.id.btn_layout1, (ImageView) findViewById(R.id.image_tab1));
        mapImageViews.put(R.id.btn_layout2, (ImageView) findViewById(R.id.image_tab2));
        mapImageViews.put(R.id.btn_layout3, (ImageView) findViewById(R.id.image_tab3));
        mapImageViews.put(R.id.btn_layout4, (ImageView) findViewById(R.id.image_tab4));
        mapTextViews = new HashMap<Integer, TextView>();
        mapTextViews.put(R.id.btn_layout1, (TextView) findViewById(R.id.text_tab1));
        mapTextViews.put(R.id.btn_layout2, (TextView) findViewById(R.id.text_tab2));
        mapTextViews.put(R.id.btn_layout3, (TextView) findViewById(R.id.text_tab3));
        mapTextViews.put(R.id.btn_layout4, (TextView) findViewById(R.id.text_tab4));
        //监听
        for (int i = 0; i < lstLayouts.size(); i++) {
            lstLayouts.get(i).setOnClickListener(this);
        }
        //刷新界面
        refreshDetail(R.id.btn_layout1);
    }

    @Override
    public void onClick(View v) {
        if (currentTabID == v.getId()) {
            return;
        }
        //刷新界面
        refreshDetail(v.getId());
    }

    //切换Tab显示按钮
    private void changeTabButton(int resId) {
        for (int i = 0; i < lstLayouts.size(); i++) {
            LinearLayout layout = lstLayouts.get(i);
            boolean isSelect = layout.getId() == resId;
//            layout.setBackgroundColor(getResources().getColor(isSelect ? R.color.bottom_bg_select : R.color.bottom_bg_normal));
            mapTextViews.get(layout.getId()).setTextColor(getResources().getColor(isSelect ? R.color.themecolor : R.color.textcolor));
            int drawableId = 0;
            switch (layout.getId()) {
                case R.id.btn_layout1:
                    drawableId = isSelect ? R.drawable.loading_0 : R.drawable.ic_launcher;
                    break;
                case R.id.btn_layout2:
                    drawableId = isSelect ? R.drawable.loading_0 : R.drawable.ic_launcher;
                    break;
                case R.id.btn_layout3:
                    drawableId = isSelect ? R.drawable.loading_0 : R.drawable.ic_launcher;
                    break;
                case R.id.btn_layout4:
                    drawableId = isSelect ? R.drawable.loading_0 : R.drawable.ic_launcher;
                    break;
                default:
                    continue;
            }
            HelperMethod.setBackgroundDrawable(MainActivity.this, mapImageViews.get(layout.getId()), drawableId);
        }
    }

    //刷新界面
    private void refreshDetail(final int resId) {
        Fragment fragment = mapFragments.get(resId);
        currentTabID = resId;
        //切换Tab显示按钮
        changeTabButton(resId);
        //参数
//        Bundle bundle = new Bundle();
//        bundle.putString("data", "参数");
//        fragment.setArguments(bundle);
        //显示界面
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BaseApplication.getInstance().exit();
    }
}
