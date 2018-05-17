package com.liu.abing.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.abing.steps.StepsHActivity;
import com.liu.extend.entity.CityBean;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/11/7 16:57
 * 修改人：Administrator
 * 修改时间：2016/11/7 16:57
 * 修改备注：
 */
public class Fragment4 extends Fragment {

    private View view;

    private TextView txt_title;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null)
        {
            Logger.d(savedInstanceState);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (view==null)
//        {
            view =inflater.inflate(R.layout.fragment_four, container, false);
        EventBus.getDefault().register(this);
            initView();
//        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        if (outState!=null)
        {
            Logger.d("11111111111"+outState);
        }
    }

    private void initView(){

        txt_title= (TextView) view.findViewById(R.id.txt_title);
        txt_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), StepsHActivity.class));
            }
        });
    }
    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String cityBean){

        txt_title.setText(cityBean);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

//    public static boolean checkPermission(Context context, String permission) {
//        boolean result = false;
//        if (Build.VERSION.SDK_INT >= 23) {
//            try {
//                Class<?> clazz = Class.forName("android.content.Context");
//                Method method = clazz.getMethod("checkSelfPermission", String.class);
//                int rest = (Integer) method.invoke(context, permission);
//                if (rest == PackageManager.PERMISSION_GRANTED) {
//                    result = true;
//                } else {
//                    result = false;
//                }
//            } catch (Exception e) {
//                result = false;
//            }
//        } else {
//            PackageManager pm = context.getPackageManager();
//            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
//                result = true;
//            }
//        }
//        return result;
////    }
//    public static String getDeviceInfo(Context context) {
//        try {
//            org.json.JSONObject json = new org.json.JSONObject();
//            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//            String device_id = null;
//            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
//                device_id = tm.getDeviceId();
//            }
//            String mac = null;
//            FileReader fstream = null;
//            try {
//                fstream = new FileReader("/sys/class/net/wlan0/address");
//            } catch (FileNotFoundException e) {
//                fstream = new FileReader("/sys/class/net/eth0/address");
//            }
//            BufferedReader in = null;
//            if (fstream != null) {
//                try {
//                    in = new BufferedReader(fstream, 1024);
//                    mac = in.readLine();
//                } catch (IOException e) {
//                } finally {
//                    if (fstream != null) {
//                        try {
//                            fstream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (in != null) {
//                        try {
//                            in.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            json.put("mac", mac);
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = mac;
//            }
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
//                        android.provider.Settings.Secure.ANDROID_ID);
//            }
//            json.put("device_id", device_id);
//            return json.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
