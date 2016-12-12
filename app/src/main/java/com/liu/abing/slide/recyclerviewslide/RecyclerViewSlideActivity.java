package com.liu.abing.slide.recyclerviewslide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by yuantongqin on 2016/11/16.
 */

public class RecyclerViewSlideActivity extends BaseActivity {

    private RecyclerView recycler;
    private TextView text_index;
    private PageIndicatorView pageIndicatorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewslide);

        initView();
    }
    private void initView()
    {
        text_index= (TextView) findViewById(R.id.text_index);
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        pageIndicatorView= (PageIndicatorView) findViewById(R.id.indicator);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(manager);
        final List<String> mlist = new ArrayList<>();
        String[] images = {"http://desk.fd.zol-img.com.cn/t_s960x600c5/g4/M01/0D/04/Cg-4WVP_npmIY6GRAKcKYPPMR3wAAQ8LgNIuTMApwp4015.jpg",
                "http://img2.3lian.com/2014/f2/164/d/16.jpg",
                "http://www.86ps.com/uploadfiles/jpg/2011-11/2011110210441978760.jpg",
                "http://img3.3lian.com/2013/s1/36/d/106.jpg",
                "http://img2.3lian.com/2014/f2/164/d/22.jpg",
                "http://pic.58pic.com/58pic/10/96/57/16x58PICs3h.jpg",
                "http://pic28.nipic.com/20130413/9903614_120926192107_2.jpg",
                "http://img1.3lian.com/2015/a1/116/d/155.jpg",
                "http://pic18.nipic.com/20111230/9040884_102858425327_2.jpg",
        };
        mlist.addAll(Arrays.asList(images));
        MyRecyclerAdaper adaper = new MyRecyclerAdaper(mlist,this);
        recycler.setAdapter(adaper);
        final RecyclerScaleUtils utils = new RecyclerScaleUtils();
        utils.attachToRecyclerView(recycler,ScreenUtils.dip2px(this,30f));
//        utils.setItemPosition(1);//设置当前页
        pageIndicatorView.initIndicator(mlist.size());//设置一共多少页
        utils.setOnIndexListener(new RecyclerScaleUtils.OnIndexListener() {
            @Override
            public void myIndexListener(int currentIndex) {
                pageIndicatorView.setSelectedPage(currentIndex);
                text_index.setText(currentIndex+"/"+mlist.size());
            }
        });
    }

}
