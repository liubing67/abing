package com.liu.abing.slide;

import android.os.Bundle;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.abing.slide.recyclerviewslide.RecyclerViewSlideActivity;
import com.tools.Tools;
import com.tools.util.ToastUtil;
import com.tools.views.UpRollView;
import com.tools.views.viewpager.LViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：abing
 * 类描述：111
 * 创建人：liubing
 * 创建时间：2016/12/12 11:25
 * 修改人：Administrator
 * 修改时间：2016/12/12 11:25
 * 修改备注：
 */
public class SlideActivity extends BaseActivity {

    LViewPager lviewpager;
    private UpRollView roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        initView();
    }
    private void initView()
    {
        // Viewpager 设置
        lviewpager= (LViewPager) findViewById(R.id.lviewpager);
        List<String> list=new ArrayList<String>();
        list.add("http://misc02.china-madpay.com//group1//M00//1B//08//tKkRB1fNSwmAZBeFAACh1izuNSA271.jpg");
        list.add("http://misc02.china-madpay.com//group1//M00//1B//08//tKkRB1fNSwmAZBeFAACh1izuNSA271.jpg");
        list.add("http://misc02.china-madpay.com//group1//M00//1B//08//tKkRB1fNSwmAZBeFAACh1izuNSA271.jpg");
        lviewpager.setImageUrl(list);

        lviewpager.setOnItemClickListener(new LViewPager.onItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                ToastUtil.customShow(SlideActivity.this,postion+"");
            }
        });


        //UpRollView设置
        roll= (UpRollView) findViewById(R.id.roll);
        roll.setText("广告111111", "广告222222222222手机充费不要钱啦");
        roll.setImageId(R.mipmap.ic_launcher, R.mipmap.ic_launcher);


        //RecyclerViews 广告设置
        findViewById(R.id.but_recycard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(SlideActivity.this,null, RecyclerViewSlideActivity.class);
            }
        });
    }
}
