package com.tools.util;

import android.os.Handler;

/**
 * 1. 可以将某段代码放在子线程执行
 * 2. 可以将某段代码切换到主线程执行
 */
public class ThreadUtils {

    /**
     * 在子线程执行
     *
     * @param runnable
     */
    public static void runOnBackThread(Runnable runnable) {
//        new Thread(runnable).start();
        ThreadPoolManager.getInstance().create().execute(runnable);
    }

    private static Handler sHandler = new Handler();

    /**
     * 切换到主线程执行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        sHandler.post(runnable);
    }
}
