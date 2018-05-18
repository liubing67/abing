package com.tools.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器
 */
public class ThreadPoolManager {

    /**
     * 获取cpu核心数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private ThreadPoolProxy mProxy;

    private ThreadPoolManager() {
    }

    // 单例  饿汉式(线程安全)
    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    /**
     * cpu * 2 + 1 效率最高
     *
     * @return
     */
    public ThreadPoolProxy create() {
        if (mProxy == null) {
            mProxy = new ThreadPoolProxy(CPU_COUNT * 2 + 1, CPU_COUNT * 2 + 1, 5000);
        }
        return mProxy;
    }


    public class ThreadPoolProxy {
        //线程池对象
        ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 执行线程任务
         *
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (threadPool == null) {
                /**
                 * 1. corePoolSize  线程池初始化的线程数量
                 * 2. maximumPoolSize 线程池 中线程上限
                 * 3.LinkedBlockingQueue   队列(最多128个任务等待)
                 */
                threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(128));
            }

            threadPool.execute(runnable);
        }

        /**
         * 取消任务
         *
         * @param runnable 不能停止，不能崩溃才能取消任务
         */
        public void cancel(Runnable runnable) {
            if (threadPool != null && !threadPool.isTerminated() && !threadPool.isShutdown()) {
                threadPool.remove(runnable);
            }
        }
    }
}
