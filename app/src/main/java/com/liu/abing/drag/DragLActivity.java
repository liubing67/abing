package com.liu.abing.drag;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.extend.entity.Cheeses;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.tools.views.DragLayout;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/22 10:01
 * 修改人：Administrator
 * 修改时间：2016/12/22 10:01
 * 修改备注：
 */
public class DragLActivity extends BaseActivity {

    private ListView lv_left;
    private ListView lv_main;
    private ImageView iv_header;
    private DragLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        initView();
    }
    private void initView()
    {
        iv_header = (ImageView) findViewById(R.id.iv_header);
        lv_left = (ListView) findViewById(R.id.lv_left);
        lv_left.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView)view).setTextColor(Color.WHITE);
                return view;
            }
        });
        lv_main = (ListView) findViewById(R.id.lv_main);
        lv_main.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.NAMES));
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setOnDragUpdateListener(new DragLayout.OnDragUpdateListener() {
            @Override
            public void onOpen() {
            }
            @Override
            public void onDraging(float percent) {
                ViewHelper.setAlpha(iv_header, 1 - percent);
            }
            @Override
            public void onClose() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv_header, "translationX", 15f);
                animator.setInterpolator(new CycleInterpolator(4));
                animator.setDuration(500);
                animator.start();
            }
        });
        iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.open(true);
            }
        });
    }
}
