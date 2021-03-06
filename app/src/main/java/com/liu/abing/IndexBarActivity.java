package com.liu.abing;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.liu.abing.base.BaseActivity;
import com.liu.extend.adapters.SwipeDelMenuAdapter;
import com.liu.extend.entity.CityBean;
import com.tools.views.indexbar.DividerItemDecoration;
import com.tools.views.indexbar.IndexBar;
import com.tools.views.indexbar.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/23 10:56
 * 修改人：Administrator
 * 修改时间：2016/12/23 10:56
 * 修改备注：
 */
public class IndexBarActivity extends BaseActivity {


    private static final String INDEX_STRING_TOP = "↑";
    private RecyclerView mRv;
    private SwipeDelMenuAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas = new ArrayList<>();

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexbar);

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));


        mAdapter = new SwipeDelMenuAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
//        mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
        mRv.addItemDecoration(new DividerItemDecoration(IndexBarActivity.this, DividerItemDecoration.VERTICAL_LIST));


        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        //模拟线上加载数据
        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟两秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                //微信的头部 也是可以右侧IndexBar导航索引的，
                // 但是它不需要被ItemDecoration设一个标题titile
                mDatas.add((CityBean) new CityBean("新的朋友").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("群聊").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("标签").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                mDatas.add((CityBean) new CityBean("公众号").setTop(true).setBaseIndexTag(INDEX_STRING_TOP));
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }
                mAdapter.setDatas(mDatas);
                mAdapter.notifyDataSetChanged();

                mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                        .setNeedRealIndex(true)//设置需要真实的索引
                        .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                        .setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mDecoration.setmDatas(mDatas);
            }
        }, 0);
    }
}
