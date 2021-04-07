package com.vilin.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FutureExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testIsDone();
        testCancel();
//        testCancel2();
    }

    private static void testIsDone() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

//        Future<Integer> Future = executorService.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 10;
//        });
//
//        System.out.println(Future.isDone());
//        Integer result = Future.get();
//        System.out.println("result = " + result);
//        System.out.println(Future.isDone());

        Future<Integer> future = executorService.submit(() -> {
            throw new RuntimeException();
        });

        try {
            Integer result = future.get();
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(future.isDone());
        }


    }

    /**
     * 当创建了Future实例，任务可能有以下三种状态：
     *
     * 等待状态。此时调用cancel()方法不管传入true还是false都会标记为取消，任务依然保存在任务队列中，但当轮到此任务运行时会直接跳过。
     * 完成状态。此时cancel()不会起任何作用，因为任务已经完成了。
     * 运行中。此时传入true会中断正在执行的任务，传入false则不会中断。
     *
     * Future.cancel(true)适用于：
     * 1. 长时间处于运行的任务，并且能够处理interruption
     *
     * Future.cancel(false)适用于：
     * 1. 未能处理interruption的任务
     * 2. 不清楚任务是否支持取消
     * 3. 需要等待已经开始的任务执行完成
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testCancel() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        Future<Integer> Future = executorService.submit(() -> 10);
//        System.out.println(Future.get());
//        System.out.println(Future.cancel(true));

        Future future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            for(int i = 0;i < 1000000; i++){
//                System.out.println("i = " + i);
//            }

            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
    }

    /**
     * 成功的cancel是指给线程传递interrupt信号量,可以配合线程的中断信号一起使用可以终止线程
     * @throws InterruptedException
     */
    private static void testCancel2() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread();
                t.setDaemon(true);
                return t;
            }
        });

        AtomicBoolean flag = new AtomicBoolean(true);

        Future future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("========================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            while(!Thread.interrupted()){
//
//            }

//            while(flag.get()){
//
//            }
            System.out.println("1111111111111111111");
            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.get());
    }
}
