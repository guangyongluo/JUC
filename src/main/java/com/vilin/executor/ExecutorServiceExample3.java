package com.vilin.executor;

import com.vilin.juc.c001.T;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceExample3 {


    public static void main(String[] args) {
//        test();
//        testAllowCoreThreadTimeOut();
//        testRemove();
//        testPreStartCoreThread();
//        testPreStartAllCoreThread();
        testThreadPoolAdvice();
    }

    private static void test() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executorService.getActiveCount());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executorService.getActiveCount());

    }

    private static void testAllowCoreThreadTimeOut(){
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);

        IntStream.range(0, 5).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

    }

    private static void testRemove() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);

        IntStream.range(0, 2).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("========== I am finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Runnable r = () -> {
            System.out.println("I will never be executed!");
        };
        executorService.execute(r);
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.remove(r);
    }

    private static void testPreStartCoreThread() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executorService.getActiveCount());

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());

        System.out.println(executorService.prestartCoreThread());
        System.out.println(executorService.getActiveCount());

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(executorService.getActiveCount());
    }

    private static void testPreStartAllCoreThread(){
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.setMaximumPoolSize(3);
        System.out.println(executorService.getActiveCount());

        System.out.println(executorService.prestartAllCoreThreads());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executorService.getActiveCount());

    }

    private static void testThreadPoolAdvice(){
        ThreadPoolExecutor executorService = new MyThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        executorService.execute(new MyRunnable(1) {
            @Override
            public void run() {
                System.out.println("=========================");
//                System.out.println(1/0);
            }
        });
    }

    private abstract static class MyRunnable implements Runnable {

        protected final int no;

        public MyRunnable(int no) {
            this.no = no;
        }

        protected int getData(){
            return this.no;
        }

    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor{

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println(t.getName());
            System.out.println("init the " + ((MyRunnable)r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if(null == t){
                System.out.println("successful " + ((MyRunnable)r).getData());
            } else {
                t.printStackTrace();
            }
        }
    }
}
