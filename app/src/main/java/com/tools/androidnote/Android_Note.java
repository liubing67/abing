package com.tools.androidnote;

import android.view.WindowManager;

import com.orhanobut.logger.Logger;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/20 17:32
 * 修改人：Administrator
 * 修改时间：2017/1/20 17:32
 * 修改备注：
 */
public class Android_Note {


    //1
    //string 转double 保留两位小数
//    Double d = Double.parseDouble("12222");
//    Logger.d(String.format("%.2f",d));


//2 改变屏幕的透明度
// 0f<=bgAlpha<=1f
//public void backgroundAlpha(float bgAlpha)
//{
//    WindowManager.LayoutParams lp = getWindow().getAttributes();
//    lp.alpha = bgAlpha; //0.0-1.0
//    getWindow().setAttributes(lp);
//}


//3
//    private void full(boolean enable) {
//        if (enable) {//隐藏状态栏
//            WindowManager.LayoutParams lp = getWindow().getAttributes();
//            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            getWindow().setAttributes(lp);
//        } else {//显示状态栏
//            WindowManager.LayoutParams attr = getWindow().getAttributes();
//            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().setAttributes(attr);
//        }
//    }


}
