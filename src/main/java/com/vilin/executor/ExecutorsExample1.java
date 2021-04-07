package com.vilin.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorsExample1 {


    public static void main(String[] args) {

//        useCachedThreadPool();
//        useFixedSizePool();
        useSinglePool();
    }

    /**
     * singleThreadExecutor difference between one thread.
     *
     * thread will die after finish work, but singleThreadExecutor can always alive.
     * Thread can not put the submitted runnable to the cache queue but
     * singleThreadExecutor can do this.
     *
     * <p>
     *     new FinalizableDelegatedExecutorService
     *             (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
     *                                     new LinkedBlockingQueue<Runnable>()));
     *
     * ==========
     * Executors.newFixedThreadPool(1);
     * </p>
     */
    private static void useSinglePool(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10).boxed().forEach(i -> executorService
                .execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "[" + i + "]");
                }));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads,
     *     0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
     *
     */
    private static void useFixedSizePool(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService
                .execute(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "[" + i + "]");
                }));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
    }

    /**
     * these pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     *
     * <p>
     *  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *    60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
     * </p>
     */
    private static void useCachedThreadPool(){

        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        executorService.execute(() -> System.out.println("=============="));

        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService
            .execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[" + i + "]");
            }));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(((ThreadPoolExecutor)executorService).getActiveCount());
    }

}
