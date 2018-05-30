package com.liu.abing.home;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.liu.abing.ColorGradActivity;
import com.liu.abing.CusEditActivity;
import com.liu.abing.EasyFlipActivity;
import com.liu.abing.FastBlurActivity;
import com.liu.abing.HintPopWActivity;
import com.liu.abing.IndexBarActivity;
import com.liu.abing.ListEditActivity;
import com.liu.abing.recyclertab.*;
import com.liu.abing.recyclerviewtest.RecyclerViewTestActivity;
import com.liu.abing.ToumingActivity;
import com.liu.abing.VersionActivity;
import com.liu.abing.WaveActivity;
import com.liu.abing.ZoominActivity;
import com.liu.abing.animator.AnimatorActivity;
import com.liu.abing.chart.ChartActivity;
import com.liu.abing.dialog.SimpleHomeActivity;
import com.liu.abing.drag.DragActivity;
import com.liu.abing.R;
import com.liu.abing.dropdownmenu.DropDownMenuActivity;
import com.liu.abing.flow.FlowActivity;
import com.liu.abing.fragment.FragActivity;
import com.liu.abing.handwrite.SignUsActivity;
import com.liu.abing.highlight.HighlightActivity;
import com.liu.abing.network.NetworkRequestActivity;
import com.liu.abing.refresh.RefreshActivity;
import com.liu.abing.roll.TextViewRollActivity;
import com.liu.abing.securitycode.SecurityCodeActivity;
import com.liu.abing.shopcar.ShopCarActivity;
import com.liu.abing.steps.StepsHActivity;
import com.liu.abing.stickynav.StickyNavActiviy;
import com.liu.abing.vlayout.VLayoutActivity;
import com.liu.abing.weiboline.*;
import com.liu.abing.wheel.WheelActivity;
import com.tools.Tools;
import com.tools.util.DownloadUtils;
import com.tools.views.ObservableScrollView;
import com.tools.views.ScrollViewListener;

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
    private Button button;
    private ObservableScrollView seroll;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_two, container, false);
        initView();
        return view;
    }

    private void initView()
    {

        button= (Button) view.findViewById(R.id.button);
        seroll= (ObservableScrollView) view.findViewById(R.id.seroll);
        initData();
        view.findViewById(R.id.but_Slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Tools.startActivity(getActivity(),null, SlideActivity.class);
                String BOARD = Build.BOARD;
                String DEVICE = Build.DEVICE;
                String DISPLAY = Build.DISPLAY;
                String FINGERPRINT = Build.FINGERPRINT;
                String HARDWARE = Build.HARDWARE;
                String HOST = Build.HOST;
                String MANUFACTURER = Build.MANUFACTURER;
                String USER = Build.USER;
                String ID = Build.ID;
                String MODEL = Build.MODEL;
                String PRODUCT = Build.PRODUCT;
                String SERIAL = Build.SERIAL;
                String TAGS = Build.TAGS;
                String TYPE = Build.TYPE;
                String BRAND  = Build.BRAND ;
                String BOOTLOADER  = Build.BOOTLOADER ;
                StringBuilder aaa = new StringBuilder();
                aaa.append("\nBOARD = " + BOARD);
                aaa.append("\nDEVICE = " + DEVICE);
                aaa.append("\nDISPLAY = " +DISPLAY);
                aaa.append("\nFINGERPRINT = " + FINGERPRINT);
                aaa.append("\nHARDWARE = " + HARDWARE);
                aaa.append("\nHOST = " +HOST);
                aaa.append("\nMANUFACTURER = " + MANUFACTURER);
                aaa.append("\nUSER = " + USER);
                aaa.append("\nID = " + ID);
                aaa.append("\nMODEL = " + MODEL);
                aaa.append("\nPRODUCT = " + PRODUCT);
                aaa.append("\nSERIAL = " + SERIAL);
                aaa.append("\nTAGS = " + TAGS);
                aaa.append("\nTYPE = " + TYPE);
                aaa.append("\nBRAND  = " + BRAND );
                aaa.append("\nBOOTLOADER  = " + BOOTLOADER );
                aaa.append("\nHARDWARE   = " + HARDWARE  );
                aaa.append("\nSERIAL    = " + SERIAL   );
                Log.e("infoaaa", Tools.getDeviceInfo());
                TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                StringBuilder sb = new StringBuilder();
                sb.append("\nDeviceId(IMEI) = " + tm.getDeviceId());
                sb.append("\nDeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion());
                sb.append("\nLine1Number = " + tm.getLine1Number());
                sb.append("\nNetworkCountryIso = " + tm.getNetworkCountryIso());
                sb.append("\nNetworkOperator = " + tm.getNetworkOperator());
                sb.append("\nNetworkOperatorName = " + tm.getNetworkOperatorName());
                sb.append("\nNetworkType = " + tm.getNetworkType());
                sb.append("\nPhoneType = " + tm.getPhoneType());
                sb.append("\nSimCountryIso = " + tm.getSimCountryIso());
                sb.append("\nSimOperator = " + tm.getSimOperator());
                sb.append("\nSimOperatorName = " + tm.getSimOperatorName());
                sb.append("\nSimSerialNumber = " + tm.getSimSerialNumber());
                sb.append("\nSimState = " + tm.getSimState());
                sb.append("\nSubscriberId(IMSI) = " + tm.getSubscriberId());
                sb.append("\nVoiceMailNumber = " + tm.getVoiceMailNumber());
                Log.e("info", sb.toString());
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
        view.findViewById(R.id.but_RecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,RecyclerViewTestActivity.class);
            }
        });
        view.findViewById(R.id.but_SecurityCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,SecurityCodeActivity.class);
            }
        });
        view.findViewById(R.id.but_flow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,FlowActivity.class);
            }
        });
        view.findViewById(R.id.but_powerful).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,ToumingActivity.class);
            }
        });
        view.findViewById(R.id.but_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,AnimatorActivity.class);
            }
        });
        view.findViewById(R.id.but_frag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,FragActivity.class);
            }
        });
        view.findViewById(R.id.but_wheel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,WheelActivity.class);
            }
        });
        view.findViewById(R.id.but_waveview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,WaveActivity.class);
            }
        });
        view.findViewById(R.id.but_customCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,WaveActivity.class);
            }
        });
        view.findViewById(R.id.but_customEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null,CusEditActivity.class);
            }
        });
        view.findViewById(R.id.but_weiboline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, WeiBoLineActivity.class);
            }
        });
        view.findViewById(R.id.but_vlayoout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, VLayoutActivity.class);
            }
        });
        view.findViewById(R.id.but_RecyclerView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startActivity(getActivity(),null, com.liu.abing.recyclertab.MainActivity.class);
            }
        });

    }
    private void initData()
    {
        seroll.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y>300)
                {
                    button.setVisibility(View.GONE);
                }else {
                    button.setVisibility(View.VISIBLE);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopw();
            }
        });
    }
    //弹出管理popuwin
    private void initPopw() {
        final PopupWindow popupWindow;
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.ppw_menu, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

}
