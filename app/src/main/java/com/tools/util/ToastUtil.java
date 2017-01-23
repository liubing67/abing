package com.tools.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.abing.R;

/**
 * Toast统一管理类
 * Created by Administrator on 2015/5/1.
 */
public class ToastUtil {

    private static boolean isCustom = false;
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        // toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
//    public static void showLong(Context context, CharSequence message) {
//        if (isCustom) {
//            if (null == toast) {
//                toast = new Toast(context);
//                toast.setDuration(Toast.LENGTH_LONG);
////            toast.setGravity(Gravity.CENTER, 0, 0);
//            }
//            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);
//            TextView txt = (TextView) toastRoot.findViewById(R.id.message);
//            txt.setText(message);
//            toast.setView(toastRoot);
//        } else {
//            if (null == toast) {
//                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
//                // toast.setGravity(Gravity.CENTER, 0, 0);
//            } else {
//                toast.setText(message);
//            }
//        }
//        toast.show();
//    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
//    public static void showLong(Context context, int message) {
//        if (isCustom) {
//            if (null == toast) {
//                toast = new Toast(context);
//                toast.setDuration(Toast.LENGTH_LONG);
////            toast.setGravity(Gravity.CENTER, 0, 0);
//            }
//            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);
//            TextView txt = (TextView) toastRoot.findViewById(R.id.message);
//            txt.setText(context.getResources().getText(message));
//            toast.setView(toastRoot);
//        } else {
//            if (null == toast) {
//                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
//                // toast.setGravity(Gravity.CENTER, 0, 0);
//            } else {
//                toast.setText(message);
//            }
//        }
//        toast.show();
//    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
//    public static void show(Context context, CharSequence message, int duration) {
//        if (isCustom) {
//            if (null == toast) {
//                toast = new Toast(context);
//                toast.setDuration(duration);
////            toast.setGravity(Gravity.CENTER, 0, 0);
//            }
//            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);
//            TextView txt = (TextView) toastRoot.findViewById(R.id.message);
//            txt.setText(message);
//            toast.setView(toastRoot);
//        } else {
//            if (null == toast) {
//                toast = Toast.makeText(context, message, duration);
//                // toast.setGravity(Gravity.CENTER, 0, 0);
//            } else {
//                toast.setText(message);
//            }
//        }
//        toast.show();
//    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
//    public static void show(Context context, int message, int duration) {
//        if (isCustom) {
//            if (null == toast) {
//                toast = new Toast(context);
//                toast.setDuration(duration);
////            toast.setGravity(Gravity.CENTER, 0, 0);
//            }
//            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);
//            TextView txt = (TextView) toastRoot.findViewById(R.id.message);
//            txt.setText(context.getResources().getText(message));
//            toast.setView(toastRoot);
//        } else {
//            if (null == toast) {
//                toast = Toast.makeText(context, message, duration);
//                // toast.setGravity(Gravity.CENTER, 0, 0);
//            } else {
//                toast.setText(message);
//            }
//        }
//        toast.show();
//    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }

    public static void customShow(Context context, String message) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_custom, null);

        TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
        text.setText(message);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setView(layout);
        toast.show();
    }

    public static void customShow(Context context, int resource) {
        String strtext = context.getResources().getString(resource);
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_custom, null);

        TextView text = (TextView) layout.findViewById(R.id.tvTextToast);
        text.setText(strtext);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setView(layout);
        toast.show();
    }

}