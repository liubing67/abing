package com.liu.abing;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liu.abing.base.BaseActivity;
import com.tools.views.HintPopupWindow;

import java.util.ArrayList;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/28 20:25
 * 修改人：Administrator
 * 修改时间：2016/12/28 20:25
 * 修改备注：
 */
public class HintPopWActivity extends BaseActivity {

    private HintPopupWindow hintPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hintpopw);

        initView();
    }
    private void initView()
    {

        //下面的操作是初始化弹出数据
        ArrayList<String> strList = new ArrayList<>();
        strList.add("选项item1");
        strList.add("选项item2");
        strList.add("选项item3");

        //具体初始化逻辑看下面的图
        hintPopupWindow = new HintPopupWindow(this, strList);
        hintPopupWindow.setOnItemClickListener(new HintPopupWindow.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int postion) {
                Toast.makeText(HintPopWActivity.this, "点击事件触发"+postion, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintPopupWindow.showPopupWindow(v);
            }
        });
    }
}
