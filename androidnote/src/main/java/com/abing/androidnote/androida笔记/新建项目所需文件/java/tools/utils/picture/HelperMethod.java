package com.abing.androidnote.androida笔记.新建项目所需文件.java.tools.utils.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
public class HelperMethod {

    /*
    根据路径设置显示的图片
     */
    public static void setBackgroundDrawable(Context context,ImageView imageView,String path)
    {
        imageView.setImageBitmap(bitmapFactory(path));
    }
    private static Bitmap bitmapFactory(String path)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options. inJustDecodeBounds = true ;
        BitmapFactory. decodeFile(path, options);

        options. inSampleSize = 2;

        options. inJustDecodeBounds = false ;
        return BitmapFactory.decodeFile(path, options);
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
            Bitmap bm = ((BitmapDrawable) view.getBackground()).getBitmap();
            view.setBackgroundResource(0);
            if (bm != null && !bm.isRecycled()) {
                bm.recycle();
                bm = null;
            }
        }
        System.gc();
    }

    public static void clearImageDrawable(ImageView view) {
        if (view != null && view.getDrawable() instanceof BitmapDrawable) {
            Bitmap bm = ((BitmapDrawable) view.getDrawable()).getBitmap();
            view.setImageDrawable(null);
            if (bm != null && !bm.isRecycled()) {
                bm.recycle();
                bm = null;
            }
        }
        System.gc();
    }
}
