package com.tools;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.liu.abing.R;
import com.loc.p;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2015/12/2.
 */
public class Tools {
    /**
     * 0x00 GetMoneyActivity
     * 0x01 AdviceToUsActivity
     */

    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat formatDay = new SimpleDateFormat("d", Locale.getDefault());
    public static SimpleDateFormat formatMonthDay = new SimpleDateFormat("M-d", Locale.getDefault());
    public static SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /**
     * 打开另一个Activity
     *
     * @param ctx
     * @param b
     * @param cls
     */
    public static void startActivity(Context ctx, Bundle b, Class cls) {
        Intent in = new Intent();
        in.setClass(ctx, cls);
        if (b != null) {
            in.putExtras(b);
        }
        ctx.startActivity(in);


    }


    /**
     * //打开另一个activity回调
     *
     * @param ctx
     * @param b
     * @param cls
     * @param requestCode
     */
    public static void startActivityForResult(Activity ctx, Bundle b, Class cls, int requestCode) {
        Intent in = new Intent();
        in.setClass(ctx, cls);
        if (b != null) {
            in.putExtras(b);
        }
        ctx.startActivityForResult(in, requestCode);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return 年月日
     */
    public static String formatDate(Date date) {
        return formatDate.format(date);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return 年月日 时分秒
     */
    public static String formatDateTime(Date date) {
        return formatDateTime.format(date);
    }

    /**
     * 将时间戳解析成日期
     *
     * @param timeInMillis
     * @return 年月日
     */
    public static String parseDate(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        Date date = calendar.getTime();
        return formatDate(date);
    }

    /**
     * 将时间戳解析成日期
     *
     * @param timeInMillis
     * @return 年月日 时分秒
     */
    public static String parseDateTime(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        Date date = calendar.getTime();
        return formatDateTime(date);
    }

    /**
     * 解析日期
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        Date mDate = null;
        try {
            mDate = formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mDate;
    }

    /**
     * 解析日期
     *
     * @param datetime
     * @return
     */
    public static Date parseDateTime(String datetime) {
        Date mDate = null;
        try {
            mDate = formatDateTime.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mDate;
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     */
    public static void hiddenSoftInput(Activity context) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        if (params.softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
            // 隐藏软键盘
            context.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;
        }
    }

    public static double formatDouble(double number) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String result = df.format(number);
        double fnumber = Double.parseDouble(result);
        return fnumber;
    }

    /**
     * 获取手机唯一标识
     *
     * @param context
     * @return
     */
    public static String getUdid(Context context) {
        //IMIE
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String m_szImei = telephonyManager.getDeviceId();
        //系统信息
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI

                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits
        //Android ID
        String m_szAndroidID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

        //WLAN MAC Address
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();

        //BlueTooth MAC Address
        BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
        m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        String m_szBTMAC = m_BluetoothAdapter.getAddress();

        //Combined Device ID
        String m_szLongID = m_szImei + m_szDevIDShort
                + m_szAndroidID + m_szWLANMAC;
        // compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        // get md5 bytes
        byte p_md5Data[] = m.digest();
        // create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            // if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID += "0";
            // add number to string
            m_szUniqueID += Integer.toHexString(b);
        }   // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();
        Log.i("info", "这台手机的唯一标识码为： " + m_szUniqueID);
        return m_szUniqueID;
    }


    /**
     * MD5工具
     *
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5str = "";
        try {
//1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

//2 将消息变成byte数组
            byte[] input = message.getBytes("utf-8");

//3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

//4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
//把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];

            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    /**
     * 判断是否登录
     *
     * @param rtState  状态码
     * @param context  上下文
     * @param activity 当前activity
     * @return
     */
//    public static boolean judgeRtState(int rtState, final Context context, Activity activity) {
//        if (rtState == -1) {
//            Intent intent = new Intent(context, LoginActivity.class);
//            context.startActivity(intent);
//            activity.finish();
//            return false;
//        }
//        return true;
//    }

    /**
     * 对图片进行压缩
     *
     * @param filePath 图片文件的路径
     * @return 压缩后的图片文件
     */
    public static File cutDownImage(String filePath) {
        Bitmap bmp = getImage(filePath);
        return bitmapToFile(bmp);
    }

    /**
     * @param filePath 图片文件的路径
     */
    private static Bitmap getImage(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
//        Log.d("123", "getImage: " + bmp.getByteCount() / 1024);
        options.inJustDecodeBounds = false;
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        options.inSampleSize = calculateInSampleSize(options);
        bmp = BitmapFactory.decodeFile(filePath, options);
//        Log.d("123", "getImage: " + bmp.getByteCount() / 1024);
        return bmp;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options) {
        // Raw height and width of image
        int reqWidth = 480;
        int reqHeight = 720;
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    /**
     * 将压缩后的Bitmap转换为图片文件
     *
     * @param bmp
     * @return
     */
    public static File bitmapToFile(Bitmap bmp) {
        String fileName = System.currentTimeMillis() + ".jpg";
        String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "bingyaoimage" + File.separator;
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File file = new File(path + fileName);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /** 获取屏幕的宽度 */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    // 实现金额文本自动增加
    public static void autoIncrement(final TextView target, final float start,
                                     final float end, long duration) {

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private FloatEvaluator evalutor = new FloatEvaluator();
            private DecimalFormat format = new DecimalFormat("####0.0#");

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float fraction = animation.getAnimatedFraction();
                float currentValue = evalutor.evaluate(fraction, start, end);
                target.setText(format.format(currentValue));
            }
        });
        animator.setDuration(duration);
        animator.start();

    }



}
