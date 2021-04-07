package com.vilin.executor;

import com.vilin.juc.c001.T;

import java.util.concurrent.*;

public class ExecutorServiceExample2 {

    public static void main(String[] args) throws InterruptedException {
//        testAbortPolicy();
//        testDiscardPolicy();
//        testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }

    private static void testAbortPolicy() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());

        for(int i = 0; i < 3; i++){
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        threadPoolExecutor.execute(() -> {
            System.out.println("x");
        });
    }

    private static void testDiscardPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardPolicy());

        for(int i = 0; i < 3; i++){
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("===============================");
        threadPoolExecutor.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });

    }

    private static void testCallerRunsPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.CallerRunsPolicy());

        for(int i = 0; i < 3; i++){
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("===============================");
        threadPoolExecutor.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
    }

    private static void testDiscardOldestPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardOldestPolicy());

        for(int i = 0; i < 3; i++){
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("I come from lambda");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("===============================");
        threadPoolExecutor.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
    }
}
