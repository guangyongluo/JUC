package com.vilin.executor;

import java.util.concurrent.*;

public class FutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        testGet();
//        testGetTimeOut();
    }


    /**
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=====================");
            return 10;
        });

        System.out.println("i will be printed quickly.");

        Thread callThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callThread.interrupt();
        }).start();

        Integer result = future.get();

        System.out.println(result);
    }

    private static void testGetTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("=====================");

            return 10;

        });

        System.out.println("i will be printed quickly.");

        Integer result = future.get(5, TimeUnit.SECONDS);

        System.out.println(result);
    }
}
