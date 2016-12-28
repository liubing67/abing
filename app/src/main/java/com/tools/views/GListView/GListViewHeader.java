/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.tools.views.GListView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liu.abing.R;
import com.tools.util.DensityUtil;
import com.tools.views.viewpager.LViewPager;

import java.util.ArrayList;
import java.util.List;

public class GListViewHeader extends LinearLayout {
    private LinearLayout mContainer;
    private LViewPager mViewPager;

    public GListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public GListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT); // 在这可以改变初始高度
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.view_glistview_header, null);
        if (isInEditMode()) {
            return;
        }
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

        mViewPager = new LViewPager(context);
        List<String> list=new ArrayList<String>();
        list.add("http://misc02.china-madpay.com//group1//M00//1B//08//tKkRB1fNSwmAZBeFAACh1izuNSA271.jpg");
        list.add("http://misc02.china-madpay.com//group1//M00//1B//08//tKkRB1fNSwmAZBeFAACh1izuNSA271.jpg");
        list.add("http://misc02.china-madpay.com//group1//M00//1B//08//tKkRB1fNSwmAZBeFAACh1izuNSA271.jpg");
        mViewPager.setImageUrl(list);
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mViewPager.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
        mContainer.addView(mViewPager);

        setVisiableHeight(DensityUtil.dip2px(context, 150));
    }

    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
        mViewPager.setHeight(height);
    }

    public int getVisiableHeight() {
        return mContainer.getHeight();
    }

    public ViewPager getContent() {
        return mViewPager.getViewPager();
    }
}
