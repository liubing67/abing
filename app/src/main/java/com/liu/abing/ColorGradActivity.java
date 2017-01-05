package com.liu.abing;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.liu.abing.base.BaseActivity;
import com.tools.util.ColorUtil;
import com.tools.util.StatusBarUtil;
import com.tools.util.SystemUtils;
import com.tools.views.ListViewForScrollView;
import com.tools.util.SystemBarTintManager ;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/22 17:28
 * 修改人：Administrator
 * 修改时间：2016/12/22 17:28
 * 修改备注：
 */
public class ColorGradActivity extends BaseActivity {

    private List<String> list;
    private ListViewForScrollView listViewForScrollView;
    private RelativeLayout rl_bar;
    private ScrollView scrollView;
    private int marginTop;//滑动距顶部的距离
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorgrad);
        StatusBarUtil.setStatusBarTranslucent(this, false);
        initView();
    }

    private void initView() {
        listViewForScrollView= (ListViewForScrollView) findViewById(R.id.listview);
        scrollView= (ScrollView) findViewById(R.id.scrollView);
        scrollView.smoothScrollTo(0, 0);
        rl_bar= (RelativeLayout) findViewById(R.id.rl_bar);
        list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("第几个" + i);
        }
        listViewForScrollView.setAdapter(new ToolAdapter());


        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                marginTop=scrollY;
                Log.e("1111111111", oldScrollY + "---" + scrollY);
                // 处理标题栏颜色渐变
                handleTitleBarColorEvaluate();

            }
        });


        //     或者用这个方法
//        View mycenter_bar = findViewById(R.id.mycenter_bar);
//        //只对api19以上版本有效
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//            mycenter_bar.setVisibility(View.VISIBLE);
//            mycenter_bar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
//            mycenter_bar.setLayoutParams(mycenter_bar.getLayoutParams());
//        } else {
//            mycenter_bar.setVisibility(View.GONE);
//        }
    }

//    @TargetApi(19)
//    private void setTranslucentStatus(boolean on) {
//        Window win =getWindow();
//        WindowManager.LayoutParams winParams = win.getAttributes();
//        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//        if (on) {
//            winParams.flags |= bits;
//        } else {
//            winParams.flags &= ~bits;
//        }
//        win.setAttributes(winParams);
//    }

    // 处理标题栏颜色渐变
    private void handleTitleBarColorEvaluate() {
        float fraction;

        fraction =marginTop*0.01f/3;

        if (marginTop<50)
        {
            rl_bar.setBackgroundColor(getResources().getColor(R.color.transparent));
        }
        if (marginTop<300&&marginTop>50)
        {

            rl_bar.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(ColorGradActivity.this, fraction, R.color.transparent, R.color.red));
            Log.e("222222222","222222222"+fraction);
        }
        if (marginTop>300)
        {
            rl_bar.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    private class ToolAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(ColorGradActivity.this).inflate(R.layout.item, null);
                viewHolder = new ViewHolder();
                viewHolder.testview = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.testview.setText(list.get(position));
            return convertView;
        }

        class ViewHolder {
            TextView testview;
        }
    }
}
