package com.liu.abing.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017-6-1 13:41
 * 修改人：Administrator
 * 修改时间：2017-6-1 13:41
 * 修改备注：
 */
public class FragActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);



        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("HOW WE WORK", "WE WORK WITH");
        navigationTabStrip.setViewPager(viewPager,1);
    }
}
