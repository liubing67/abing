package com.liu.abing.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/3 20:02
 * 修改人：Administrator
 * 修改时间：2017/1/3 20:02
 * 修改备注：
 */
public class SwipeRefreshLayoutActivity extends BaseActivity {

    private SwipeRefreshLayout refreshableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh);

        initView();
    }
    private void initView()
    {
        refreshableView= (SwipeRefreshLayout) findViewById(R.id.refreshable_view);

        refreshableView.setColorSchemeResources(R.color.red,
                R.color.blue,
                R.color.orange,
                R.color.green);
        //首次进入刷新
//        refreshableView.post(new Runnable() {
//            @Override
//            public void run() {
//                refreshableView.setRefreshing(true);
//                new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        //刷新界面信息
//                        refreshableView.setRefreshing(false);
//                    }
//                }.sendEmptyMessageDelayed(0, 3000);
//            }
//        });
        refreshableView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //刷新界面信息
                        refreshableView.setRefreshing(false);
                    }
                }.sendEmptyMessageDelayed(0, 3000);
            }
        });
    }
}
