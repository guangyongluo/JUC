package com.vilin.juc.utils.threadpool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        //定义线程池，初始化线程数为2，核心线程数为4，最大线程数为6，任务队列最多允许1000个任务
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        //定义20个任务并且提交给线程池
        for(int i = 0; i < 20; i++){
            try {
                threadPool.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RunnableDenyException e) {
                e.printStackTrace();
            }
        }

        while(true){
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getQueueSize:" + threadPool.getQueueSize());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSize:" + threadPool.getMaxSize());
            System.out.println("=============================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
