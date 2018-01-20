package com.liu.abing.weiboline;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.liu.abing.R;
import com.liu.abing.home.Fragment1;
import com.liu.abing.home.Fragment3;
import com.liu.abing.home.Fragment4;
import com.liu.extend.adapters.ViewPagerAdapter;

import java.util.ArrayList;

public class WeiBoLineActivity extends AppCompatActivity {

    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private ArrayList<Fragment> views;
    private View view1;
    private View view2;
    private View view3;
//    private MyOnPageChangeListener onPageChangeListener;
    private DynamicLine dynamicLine;
    private ViewPagerTitle viewPagerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weboline);
        init();
    }

    private void init() {


        views = new ArrayList<>();

        viewPagerTitle = (ViewPagerTitle)findViewById(R.id.pager_title);

//        dynamicLine = (DynamicLine)findViewById(R.id.line);

        pager = (ViewPager) findViewById(R.id.view_pager);
        viewPagerTitle.initData(new String[]{"layout1", "layout2", "layout3"}, pager, 0);

//        onPageChangeListener = new MyOnPageChangeListener(pager, dynamicLine);
        viewPagerTitle.setOnTextViewClickListener(new ViewPagerTitle.OnTextViewClick() {
            @Override
            public void textViewClick(TextView textView, int index) {

            }
        });
        views = new ArrayList<>();
        views.add(new Fragment1());
        views.add(new Fragment3());
        views.add(new Fragment4());

//        pager.addOnPageChangeListener(onPageChangeListener);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),views);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }




    @Override
    protected void onDestroy() {
//        pager.removeOnPageChangeListener(onPageChangeListener);
        super.onDestroy();
    }
}
