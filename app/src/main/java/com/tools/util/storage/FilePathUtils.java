package com.tools.util.storage;

import android.content.Context;
import android.os.Environment;


import com.liu.abing.base.BaseApplication;

import java.io.File;

/**
 * 项目名称：AgentManage
 * 类描述：
 * 创建人：liubing
 * 创建时间：2018-1-8 11:42
 * 修改人：Administrator
 * 修改时间：2018-1-8 11:42
 * 修改备注：
 */
public class FilePathUtils {


    /**
     * 获取app根目录
     * @return
     */
    public static String getRootDirectory(){

        return getRootPath(BaseApplication.getInstance()).getAbsolutePath() + File.separator +
                "AgentManage";
    }

    /**
     * 得到SD卡根目录.
     */
    public static File getRootPath(Context context) {
        if (sdCardIsAvailable()) {
            return Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        } else {
            return context.getFilesDir();
        }
    }

    /**
     * SD卡是否可用.
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else{
            return false;
        }

    }
}
