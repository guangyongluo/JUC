package com.vilin.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorLognTimeTask {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 20).boxed().forEach(i ->
                executorService.execute(() -> {
                    while (true){

                    }
                })
        );

        executorService.shutdownNow();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=========start serialize work============");
    }

}
