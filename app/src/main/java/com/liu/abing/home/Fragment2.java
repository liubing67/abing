package com.liu.abing.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.MenuPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liu.abing.ColorGradActivity;
import com.liu.abing.EasyFlipActivity;
import com.liu.abing.FastBlurActivity;
import com.liu.abing.HintPopWActivity;
import com.liu.abing.IndexBarActivity;
import com.liu.abing.ListEditActivity;
import com.liu.abing.VersionActivity;
import com.liu.abing.ZoominActivity;
import com.liu.abing.chart.ChartActivity;
import com.liu.abing.dialog.LiuDialogActivity;
import com.liu.abing.dialog.SimpleHomeActivity;
import com.liu.abing.drag.DragActivity;
import com.liu.abing.drag.DragLActivity;
import com.liu.abing.R;
import com.liu.abing.drawphotoview.*;
import com.liu.abing.dropdownmenu.DropDownMenuActivity;
import com.liu.abing.handwrite.SignUsActivity;
import com.liu.abing.highlight.HighlightActivity;
import com.liu.abing.network.NetworkRequestActivity;
import com.liu.abing.refresh.RefreshActivity;
import com.liu.abing.roll.TextViewRollActivity;
import com.liu.abing.service.KeepProcessActivity;
import com.liu.abing.shopcar.ShopCarActivity;
import com.liu.abing.slide.SlideActivity;
import com.liu.abing.steps.StepsHActivity;
import com.liu.abing.stickynav.StickyNavActiviy;
import com.tools.Tools;
import com.tools.util.DownloadUtils;

import abing.liu.com.citypicker.CityPickerActivity;


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
        view.findViewById(R.id.but_roll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, TextViewRollActivity.class);
            }
        });
        view.findViewById(R.id.but_step).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, StepsHActivity.class);
            }
        });
        view.findViewById(R.id.but_drag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, DragActivity.class);
            }
        });
        view.findViewById(R.id.but_shopcar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, ShopCarActivity.class);
            }
        });
        view.findViewById(R.id.but_colorgrad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, ColorGradActivity.class);
            }
        });
        view.findViewById(R.id.but_indexBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, IndexBarActivity.class);
            }
        });
        view.findViewById(R.id.but_citypicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, CityPickerActivity.class);
            }
        });
        view.findViewById(R.id.but_Chart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, ChartActivity.class);
            }
        });
        view.findViewById(R.id.but_networkRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, NetworkRequestActivity.class);
            }
        });
        view.findViewById(R.id.but_highlight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, HighlightActivity.class);
            }
        });
        view.findViewById(R.id.but_popw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, HintPopWActivity.class);
            }
        });
        view.findViewById(R.id.but_blur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, FastBlurActivity.class);
            }
        });
        view.findViewById(R.id.but_zoomin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, ZoominActivity.class);
            }
        });
        view.findViewById(R.id.but_Refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, RefreshActivity.class);
            }
        });
        view.findViewById(R.id.but_version).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, VersionActivity.class);
            }
        });
        view.findViewById(R.id.but_dropdownMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,DropDownMenuActivity.class);
            }
        });
        view.findViewById(R.id.but_easyflip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,EasyFlipActivity.class);
            }
        });
        view.findViewById(R.id.but_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,SimpleHomeActivity.class);
            }
        });
        view.findViewById(R.id.but_stickynav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,StickyNavActiviy.class);
            }
        });
        view.findViewById(R.id.but_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,SignUsActivity.class);
            }
        });
        view.findViewById(R.id.but_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, com.liu.abing.drawphotoview.MainActivity.class);
            }
        });
        view.findViewById(R.id.but_listedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,ListEditActivity.class);
            }
        });
        view.findViewById(R.id.but_keepProcess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Tools.startActivity(getActivity(),null,KeepProcessActivity.class);
                new DownloadUtils(getActivity()).download("http://www.china-madpay.com/appcheckversion/agentmange.apk","1111");
            }
        });
    }


}
