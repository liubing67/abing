package com.liu.abing.recyclerviewtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.extend.entity.SortBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/3/23 12:00
 * 修改人：Administrator
 * 修改时间：2017/3/23 12:00
 * 修改备注：
 */
public class RecyclerViewTestActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ClassifyDetailAdapter mAdapter;
    private GridLayoutManager mManager;
    private List<SortBean> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recclertest);
        ButterKnife.bind(this);

        initView();

    }
    private void initView(){
        mManager = new GridLayoutManager(this, 4);
        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mDatas.get(position).isTitle() ? 4 : 1;
            }
        });
        recyclerView.setLayoutManager(mManager);
        mAdapter = new ClassifyDetailAdapter(this, mDatas, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                String content = "";
                switch (id) {
                    case R.id.root:
                        content = "title";
                        break;
                    case R.id.content:
                        content = "content";
                        break;

                }
                Snackbar snackbar = Snackbar.make(recyclerView, "当前点击的是" + content + ":" + mDatas.get(position).getName(), Snackbar.LENGTH_SHORT);
                View mView = snackbar.getView();
                mView.setBackgroundColor(Color.BLUE);
                TextView text = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
                text.setTextColor(Color.WHITE);
                text.setTextSize(25);
                snackbar.show();
            }
        });

        recyclerView.setAdapter(mAdapter);
        String[] title={"渠道服务","商户服务","综合服务"};
        String[] qudao={"添加代理商","分配政策","政策调整","机具划分"};
        String[] shanghu={"待签约商户","商户分配","商户名变更","资料变更","资料变更","资料变更","资料变更","资料变更","资料变更","资料变更","资料变更","资料变更"};
        String[] zonghe={"零交易商户","激活报表"};
        Integer[] zongheIcon={R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        for (int i = 0; i < title.length; i++) {
            SortBean titleBean = new SortBean(title[i]);
            titleBean.setTitle(true);//头部设置为true
            mDatas.add(titleBean);
            if (title[i].equals("渠道服务"))
            {
                for (int j = 0; j < qudao.length; j++) {
                    SortBean sortBean = new SortBean(qudao[j],0);
                    mDatas.add(sortBean);
                }
            }else if (title[i].equals("商户服务"))
            {
                for (int j = 0; j < shanghu.length; j++) {
                    if (!shanghu[j].equals("商户分配"))
                    {
                        if (!shanghu[j].equals("商户名变更")){
                            SortBean sortBean = new SortBean(shanghu[j],0);
                            mDatas.add(sortBean);
                        }

                    }

                }
            }else if (title[i].equals("综合服务"))
            {
                for (int j = 0; j < zonghe.length; j++) {
                    SortBean sortBean = new SortBean(zonghe[j],zongheIcon[j]);
                    mDatas.add(sortBean);
                }
            }


        }
        mAdapter.notifyDataSetChanged();
    }
}
