package com.tools.util.pictures;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/10/15.
 */
public class HelperMethod {

    //金额转换
    public static String toDecimal(double d, int digits) {
        String format = "%";
        if (digits > 0) {
            format += "." + digits;
        }
        format += "f";
        return String.format(format, d);
    }

    public static String toDecimal(String d, int digits) {
        return toDecimal(Double.valueOf(d), digits);
    }

    //时间转换
    public static String toTime(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String toTime(Date date) {
        return toTime(date, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static Date toTime(String date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date toTime(String date) {
        return toTime(date, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static String getWeek(int index) {
        String[] week = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        return week[index % week.length];
    }

    public static long getDiffDays(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long result = Math.abs(time2 - time1);
        return result / 1000 / 60 / 60 / 24;
    }

    //文件操作
    public static boolean deleteFile(String path) {
        File file = new File(path.replace("/mnt", ""));
        if (file == null || !file.exists() || file.isDirectory()) {
            return false;
        }
        file.delete();
        return true;
    }

    public static boolean moveFile(String oldPath, String newPath) {
        File file = new File(oldPath.replace("/mnt", ""));
        if (file == null || !file.exists() || file.isDirectory()) {
            return false;
        }
        File newFile = new File(newPath.replace("/mnt", ""));
        file.renameTo(newFile);
        return true;
    }

    //背景设置
    public static void setBackgroundDrawable(Context context, View view, int redID) {
        view.setBackgroundDrawable(new BitmapDrawable(BitmapUtil.readBitMap(context.getApplicationContext(), redID)));
    }

    public static void setBackgroundDrawable(Context context, View view, String path) {
        view.setBackgroundDrawable(new BitmapDrawable(BitmapUtil.readBitMap(context.getApplicationContext(), view.getLayoutParams(), path)));
    }

    public static void setBackgroundDrawable(Context context, View view, Bitmap bitmap) {
        view.setBackgroundDrawable(new BitmapDrawable(bitmap));
    }

    public static void setImageDrawable(Context context, ImageView view, String path) {
        view.setImageDrawable(new BitmapDrawable(BitmapUtil.readBitMap(path)));
    }

    public static void clearBackgroundDrawable(View view) {
        if (view != null && view.getBackground() != null) {
            Drawable drawable = view.getBackground();
            if (drawable != null) {
                drawable.setCallback(null);
                Bitmap bm = ((BitmapDrawable) drawable).getBitmap();
                view.setBackgroundResource(0);
                if (bm != null && !bm.isRecycled()) {
                    bm.recycle();
                    bm = null;
                }
            }
        }
        System.gc();
    }

    public static void clearImageDrawable(ImageView view) {
        if (view != null && view.getDrawable() instanceof BitmapDrawable) {
            Drawable drawable = view.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
                Bitmap bm = ((BitmapDrawable) drawable).getBitmap();
//                view.setImageDrawable(null);
                if (bm != null && !bm.isRecycled()) {
                    bm.recycle();
                    bm = null;
                }
            }
        }
        System.gc();
    }
//
//    //登录检测
//    public static boolean checkLogin(Activity context, int classCode) {
//        if (HelperShared.getIsLogin(context)) {
//            if (classCode != Common.SEND_NOTICE) {
//                Intent intent = new Intent(context, getHashMapClass().get(classCode));
//                int page = 0;
//                if (classCode == Common.OPEN_CLEAN) {
//                    page = 1;
//                } else if (classCode == Common.OPEN_REPAIR) {
//                    page = 2;
//                }
//                if (page != 0) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("page", page);
//                    intent.putExtras(bundle);
//                }
//                context.startActivityForResult(intent, classCode);
//            }
//            return true;
//        } else {
//            Intent intent = new Intent(context, LoginActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putInt("code", classCode);
//            intent.putExtras(bundle);
//            context.startActivityForResult(intent, Common.REQUEST_LOGIN);
//            return false;
//        }
//    }

//    public static HashMap<Integer, Class<?>> getHashMapClass() {
//        HashMap<Integer, Class<?>> map = new HashMap<Integer, Class<?>>();
//        map.put(Common.OPEN_PERSON_CENTER, PersonalCenterActivity.class);
//        map.put(Common.OPEN_CHECK_IN, CheckInActivity.class);
//        map.put(Common.OPEN_CHECK_OUT, CheckOutActivity.class);
//        map.put(Common.OPEN_RESTAURANT, RestaurantActivity.class);
//        map.put(Common.OPEN_SHOP, ShopActivity.class);
//        map.put(Common.OPEN_CLEAN, ServiceActivity.class);
//        map.put(Common.OPEN_REPAIR, ServiceActivity.class);
//        return map;
//    }
}
