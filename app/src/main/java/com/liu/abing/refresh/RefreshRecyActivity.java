package com.liu.abing.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.extend.adapters.RecyclerArrayAdapter;
import com.tools.views.EasyRecyclerView;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/4/6 11:53
 * 修改人：Administrator
 * 修改时间：2017/4/6 11:53
 * 修改备注：
 */
public class RefreshRecyActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{
    private EasyRecyclerView recyclerView;
    private PersonAdapter adapter;
    private Handler handler;

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_refreshrecy);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapterWithProgress(adapter = new PersonAdapter(this));
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);
        recyclerView.setRefreshListener(this);
        handler = new Handler();
        addPerson();
    }

    @Override
    public void onLoadMore() {
        addPerson();

        Log.e("11111111111111","11111111111111111");
    }

    @Override
    public void onRefresh() {
        page=0;
        addPerson();
        Log.e("222222222222222222","22222222222222222222");
    }

    public void addPerson(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Person> arr = new ArrayList<>();
                final int ipage = ++page;
                arr.add(new Person("http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg", "月の星く雪" + "————————第" + ipage + "页", "完结来补"));
                arr.add(new Person("http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg", "影·蓝玉", "一看评论被***了一脸，伐开心。"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/6247858/e779259d/myface.png", "i琳夏i", "(｀・ω・´)"));
                arr.add(new Person("http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg", "Minerva。", "为啥下载不能了？π_π"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png", "如歌行极", "求生肉（/TДT)/"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png", "GERM", "第一次看 看弹幕那些说什么影帝模式啥的 感觉日了狗了 让我怎么往后看啊 艹"));
                arr.add(new Person("http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg", "じ★ve↘魅惑", "开头吾王裙子被撩起来怎么回事！→_→"));
                arr.add(new Person("http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg", "道尘一梦", "@伪 · 卫宫士郎"));
                if (ipage == 1)adapter.clear();
                if (ipage == 4)adapter.stopMore();
                adapter.addAll(arr);
            }
        }, 1500);
    }
}

