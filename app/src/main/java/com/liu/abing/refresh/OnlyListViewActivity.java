package com.liu.abing.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demievil.library.RefreshLayout;
import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.util.ToastUtil;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/4 10:42
 * 修改人：Administrator
 * 修改时间：2017/1/4 10:42
 * 修改备注：
 */
public class OnlyListViewActivity extends BaseActivity {

    private RefreshLayout swipeContainer;
    private ListView  listviewMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlylistview);
        initView();

    }
    private void initView()
    {
        swipeContainer= (RefreshLayout) findViewById(R.id.swipe_container);
        listviewMessage= (ListView) findViewById(R.id.listview_message);
        swipeContainer.setChildView(listviewMessage);
        swipeContainer.setColorSchemeResources(R.color.user_bottom_text,
                R.color.google_green,
                R.color.light_green,
                R.color.red);
        listviewMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //下拉刷新
        swipeContainer.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //刷新界面信息
                        swipeContainer.setRefreshing(false);
                        ToastUtil.customShow(OnlyListViewActivity.this,"刷新完成");
                    }
                }.sendEmptyMessageDelayed(0, 3000);

            }
        });

        //上拉加载
        swipeContainer.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //刷新界面信息
                        swipeContainer.setLoading(false);
                        ToastUtil.customShow(OnlyListViewActivity.this,"加载完成");
                    }
                }.sendEmptyMessageDelayed(0, 3000);
            }
        });
    }
}
