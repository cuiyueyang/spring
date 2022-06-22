package com.example.demo.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 单例线程池</p>
 * <p>Date: 2021/6/17 14:08 </p>
 *
 * @version v1.0.0
 * @Author: cuiyy
 */
public class ThreadPool {

    /**
     *
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
            20,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    private static ThreadPool instance = null;

    private ThreadPool() {
    }

    /**
     * 使用线程池执行任务
     * @param runnable
     */
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * 如果有线程池任务未完成，当前主线程阻塞
     */
    public void waitBlock() {
        while (executor.getActiveCount() > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isDoneAnalyse() {
        return executor.getActiveCount() == 0;
    }

    /**
     * 获取单例的线程池对象：双锁检查实现单例线程池
     * @return
     */
    public static ThreadPool getThreadPool() {
        if (instance == null) {
            synchronized (ThreadPool.class) {
                if (instance == null) {
                    instance = new ThreadPool();
                }
            }
        }
        return  instance;
    }


}
