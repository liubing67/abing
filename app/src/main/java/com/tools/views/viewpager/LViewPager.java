package com.tools.views.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.liu.abing.R;
import com.orhanobut.logger.Logger;
import com.tools.util.ImageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：SecondPay
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/9 14:02
 * 修改人：Administrator
 * 修改时间：2016/12/9 14:02
 * 修改备注：
 */
public class LViewPager extends RelativeLayout {


    private Context context;
    private View view;
    private ViewPager viewPager;
    private LinearLayout pointViewgroup;
    private MyPageAdapter myPageAdapter;

    private List<View> picList;
    private List<String> listimageUrl;
    private final static int VP_NEXT = 0x00;
    private final static int VP_NEXT_TIME = 3000;//时间间隔
    //当前viewpager显示第几项
    private int currentItem;
    private onItemClickListener onitemclicklistener;

    public LViewPager(Context context) {
        this(context, null);

    }

    public LViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        Logger.d(context);
        initView();

    }

    public interface onItemClickListener {
        void onItemClick(int postion);
    }

    //传入数据
    public void setImageUrl(List<String> eList) {
        if (eList != null) {
            listimageUrl = eList;
            setScoreImage();
        }

    }
    //点击监听

    public void setOnItemClickListener(onItemClickListener onitem) {
        onitemclicklistener = onitem;
    }

    //控制viewpager图片自动轮播
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == VP_NEXT) {
                currentItem = viewPager.getCurrentItem();
                currentItem++;
                if (currentItem == picList.size()) {
                    currentItem = 0;
                }
                viewPager.setCurrentItem(currentItem);
            }
            handler.sendEmptyMessageDelayed(VP_NEXT, VP_NEXT_TIME);
        }
    };

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.lviewpager_layout, this, true);
        viewPager = (ViewPager) view.findViewById(R.id.pic_viewpager);
        pointViewgroup = (LinearLayout) view.findViewById(R.id.point_viewgroup);
        picList = new ArrayList<>();
        listimageUrl = new ArrayList<String>();

        myPageAdapter = new MyPageAdapter(picList);
        viewPager.setAdapter(myPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changePoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void setScoreImage() {
        addPicView();
        setPoint();
        myPageAdapter.notifyDataSetChanged();
        handler.sendEmptyMessageDelayed(VP_NEXT, VP_NEXT_TIME);
        for (int i = 0; i < listimageUrl.size(); i++) {
            ImageView iv = (ImageView) picList.get(i).findViewById(R.id.iv_iamgeview);
//            //判断点击页面是否存在，如果不存在则不对Imageview做监听
            if (listimageUrl.get(i) != null && !listimageUrl.get(i).equals("")) {
                setImageViewClick(iv, i);
            }

            new ImageManager(context).loadUrlImage(listimageUrl.get(i), iv);
        }
    }

    private void setImageViewClick(final ImageView iv, final int str) {


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemclicklistener.onItemClick(str);
            }
        });

    }

    /**
     * 装载底部图片
     */
    private void addPicView() {
        LayoutInflater mInflate = LayoutInflater.from(context);
        for (int i = 0; i < listimageUrl.size(); i++) {
            View view = mInflate.inflate(R.layout.item_viewpager, null);
            picList.add(view);
        }
    }

    private void setPoint() {
        for (int i = 0; i < listimageUrl.size(); i++) {
            ImageView iv = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 0, 0);
            iv.setLayoutParams(params);
            if (i == 0) {
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.point1));
            } else {
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.point2));
            }
            pointViewgroup.addView(iv);
        }
    }

    private void changePoint(int index) {
        for (int i = 0; i < pointViewgroup.getChildCount(); i++) {
            ImageView iv = (ImageView) pointViewgroup.getChildAt(i);
            if (i == index) {
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.point1));
            } else {
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.point2));
            }
        }
    }


    //viewPager的适配器
    private class MyPageAdapter extends PagerAdapter {
        private List<View> list;

        public MyPageAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
