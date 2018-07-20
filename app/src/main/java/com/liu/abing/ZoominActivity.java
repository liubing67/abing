package com.liu.abing;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liu.abing.base.BaseActivity;
import com.tools.views.GListView.GListView;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/28 22:23
 * 修改人：Administrator
 * 修改时间：2016/12/28 22:23
 * 修改备注：
 */
public class ZoominActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomin);

        initView();
    }
    private void initView()
    {
        GListView listView = (GListView) findViewById(R.id.listview);
        listView.setAdapter(new MAdapter());
    }
    class MAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(ZoominActivity.this);
            textView.setText("仿天猫商品详情效果");
            textView.setPadding(50, 50, 50, 50);
            return textView;
        }
    }
}