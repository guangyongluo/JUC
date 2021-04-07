package com.vilin.executor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ExecutorServiceExample4 {

    public static void main(String[] args) {

//        testInvokeAny();
        testInvokeAnyTimeOut();
//        testInvokeAll();
//        testInvokeAllTimeOut();
//        testSubmitRunnable();
//        testSubmitRunnableWithResult();
    }

    /**
     * @link ExecutorService#invokeAny(Collection)
     *
     * Question:
     *   when the result returned, other callable will be keep on process?
     *
     * Answer:
     *  Other callable will be cancel.
     *
     * Note:
     *   the invokeAny will be blocked caller.
     */
    private static void testInvokeAny(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {

            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            System.out.println(Thread.currentThread().getName() + ":" + i);
            return i;
        }).collect(toList());

        try {
            System.out.println(executorService.invokeAny(callableList));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("============finished=============");
    }

    /**
     * Question：
     *
     *
     * @link ExecutorService#invokeAny(Collection, long, TimeUnit)
     */
    private static void testInvokeAnyTimeOut(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {

            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
            System.out.println(Thread.currentThread().getName() + ":" + i);
            return i;
        }).collect(toList());

        try {
            System.out.println(executorService.invokeAny(callableList, 3, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("============finished=============");
    }

    private static void testInvokeAll(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {

            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
            System.out.println(Thread.currentThread().getName() + ":" + i);
            return i;
        }).collect(toList());

        try {
            executorService.invokeAll(callableList).parallelStream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============finished=============");
    }

    private static void testInvokeAllTimeOut(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {

            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
            System.out.println(Thread.currentThread().getName() + ":" + i);
            return i;
        }).collect(toList());

        try {
            executorService.invokeAll(callableList, 10, TimeUnit.SECONDS).parallelStream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("============finished=============");
    }

    private static void testSubmitRunnable() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Object o = null;
        try {
            o = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("return : " + o);
    }

    private static void testSubmitRunnableWithResult() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
