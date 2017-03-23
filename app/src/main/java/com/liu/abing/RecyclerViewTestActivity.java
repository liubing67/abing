package com.liu.abing;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.liu.abing.base.BaseActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recclertest);
        ButterKnife.bind(this);


    }
}
