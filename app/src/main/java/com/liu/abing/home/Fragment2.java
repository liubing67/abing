package com.liu.abing.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liu.abing.R;
import com.liu.abing.slide.SlideActivity;
import com.tools.Tools;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/11/7 16:57
 * 修改人：Administrator
 * 修改时间：2016/11/7 16:57
 * 修改备注：
 */
public class Fragment2 extends Fragment {

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_two, container, false);
        initView();
        return view;
    }

    private void initView()
    {
        view.findViewById(R.id.but_Slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, SlideActivity.class);
            }
        });
    }
}
