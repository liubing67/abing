package com.liu.abing.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.liu.abing.NewsFragment;
import com.liu.abing.R;
import com.liu.extend.adapters.NewsFragmentPagerAdapter;
import com.liu.extend.entity.Constants;
import com.liu.extend.entity.NewsClassify;
import com.tools.Tools;
import com.tools.views.ColumnHorizontalScrollView;

import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/11/7 16:57
 * 修改人：Administrator
 * 修改时间：2016/11/7 16:57
 * 修改备注：
 */
public class Fragment1 extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * 自定义HorizontalScrollView
     */
    private ColumnHorizontalScrollView mColumnHorizontalScrollView;
    LinearLayout mRadioGroup_content;
    LinearLayout ll_more_columns;
    RelativeLayout rl_column;
    private ViewPager mViewPager;
    private ImageView button_more_columns;
    /**
     * 新闻分类列表
     */
    private ArrayList<NewsClassify> newsClassify = new ArrayList<NewsClassify>();
    /**
     * 当前选中的栏目
     */
    private int columnSelectIndex = 0;
    /**
     * 左阴影部分
     */
    public ImageView shade_left;
    /**
     * 右阴影部分
     */
    public ImageView shade_right;
    /**
     * 屏幕宽度
     */
    private int mScreenWidth = 0;
    /**
     * Item宽度
     */
    private int mItemWidth = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
        mScreenWidth = Tools.getWindowsWidth(getActivity());
        mItemWidth = mScreenWidth / 7;// 一个Item宽度为屏幕的1/7
        initView();
        return view;
    }

    /**
     * 初始化layout控件
     */
    private void initView() {
        mColumnHorizontalScrollView = (ColumnHorizontalScrollView) view.findViewById(R.id.mColumnHorizontalScrollView);
        mRadioGroup_content = (LinearLayout) view.findViewById(R.id.mRadioGroup_content);
        ll_more_columns = (LinearLayout) view.findViewById(R.id.ll_more_columns);
        rl_column = (RelativeLayout) view.findViewById(R.id.rl_column);
        button_more_columns = (ImageView) view.findViewById(R.id.button_more_columns);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        shade_left = (ImageView) view.findViewById(R.id.shade_left);
        shade_right = (ImageView) view.findViewById(R.id.shade_right);
        setChangelView();
    }

    /**
     * 当栏目项发生变化时候调用
     */
    private void setChangelView() {
        initColumnData();
        initTabColumn();
        initFragment();
    }

    /**
     * 获取Column栏目 数据
     */
    private void initColumnData() {
        newsClassify = Constants.getData();
    }

    /**
     * 初始化Column栏目项
     */
    private void initTabColumn() {
        mRadioGroup_content.removeAllViews();
        int count = newsClassify.size();

        mColumnHorizontalScrollView.setParam(getActivity(), mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            final TextView columnTextView = new TextView(getActivity());
            columnTextView.setTextAppearance(getActivity(), R.style.top_category_scroll_view_item_text);
            columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(newsClassify.get(i).getTitle());
            columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
            }
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
                        View localView = mRadioGroup_content.getChildAt(i);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            mViewPager.setCurrentItem(i, false);
                        }
                    }
                }
            });
            mRadioGroup_content.addView(columnTextView, i, params);
        }
    }

    /**
     * 选择的Column里面的Tab
     */
    private void selectTab(int tab_postion) {
        columnSelectIndex = tab_postion;

        //设置mColumnHorizontalScrollView滚动的距离
        View checkView = mRadioGroup_content.getChildAt(tab_postion);
        int k = checkView.getWidth();
        int l = checkView.getLeft();
        int i2 = l + k / 2 - mScreenWidth / 2;
        mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
        Log.e("111111111", "tab_postion---" + tab_postion + "---k---" + k + "---l---" + l);


        //判断是否选中
        for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
            View checkview = mRadioGroup_content.getChildAt(j);
            boolean ischeck;
            if (j == tab_postion) {
                ischeck = true;
            } else {
                ischeck = false;
            }
            checkview.setSelected(ischeck);
        }
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        int count = newsClassify.size();
        fragments.clear();
        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
            data.putString("text", newsClassify.get(i).getTitle());
            NewsFragment newfragment = new NewsFragment();
            newfragment.setArguments(data);
            fragments.add(newfragment);
        }
        Log.e("1111111111111", fragments.size() + "");
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapetr);
        mViewPager.setOnPageChangeListener(pageListener);
    }

    /**
     * ViewPager切换监听方法
     */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mViewPager.setCurrentItem(position);
            selectTab(position);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
