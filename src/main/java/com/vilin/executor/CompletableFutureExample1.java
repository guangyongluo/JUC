package com.vilin.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CompletableFutureExample1 {


    public static void main(String[] args) throws InterruptedException {

//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).whenComplete((v, t) -> System.out.println("DONE"));
//
//        System.out.println("=========I am not blocked=========");
//
//        Thread.currentThread().join();

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
//                .map(i -> (Callable<Integer>) () -> get()).collect(toList());
//
//        executorService.invokeAll(tasks).stream().map(future ->{
//            try {
//                return future.get();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }).parallel().forEach(CompletableFutureExample1::display);

        IntStream.range(0, 10).boxed()
                .forEach(i -> CompletableFuture.supplyAsync(CompletableFutureExample1::get)
                .thenAccept(CompletableFutureExample1::display)
                .whenComplete((v, t) -> System.out.println(i + "DONE")));

        Thread.currentThread().join();
    }

    private static void display(int data){
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " display will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " display execute done " + data);
    }

    private static int get(){
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " execute done " + value);
        return value;
    }
}
