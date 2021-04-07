package com.vilin.executor;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTask {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 20).boxed().forEach(i ->
                executorService.execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(20);
                        System.out.println(Thread.currentThread().getName() + "[" + i + "] finished.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );

//        executorService.shutdown();


        List<Runnable> runnableList = executorService.shutdownNow();
        System.out.println(runnableList);
        System.out.println(runnableList.size());


//        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("==============over===============");
    }
}
