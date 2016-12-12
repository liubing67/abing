package com.liu.abing.slide;

import android.os.Bundle;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.util.ToastUtil;
import com.tools.views.viewpager.LViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/12 11:25
 * 修改人：Administrator
 * 修改时间：2016/12/12 11:25
 * 修改备注：
 */
public class SlideActivity extends BaseActivity {

    LViewPager lviewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        initView();
    }
    private void initView()
    {
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
    }
}
