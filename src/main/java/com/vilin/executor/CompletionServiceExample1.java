package com.vilin.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample1 {

    public static void main(String[] args) throws InterruptedException {
        futureDefect2();
    }

    /**
     * no callback
     */
    private static void futureDefect1(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            sleep(100);
            return 100;
        });

        System.out.println("=======================");
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void futureDefect2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callableList = Arrays.asList(
            () -> {
                sleep(10);
                System.out.println("The 10 finished");
                return 10;
            },
            () -> {
                sleep(20);
                System.out.println("The 20 finished");
                return 20;
            }
        );

        List<Future<Integer>> futureList = executorService.invokeAll(callableList);

        try {
            System.out.println(futureList.get(1).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(futureList.get(0).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void sleep(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
