package com.vilin.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample2 {

    public static void main(String[] args) {
//        supplyAsync();
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Future<Void> future = runAsync();
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        Future<Void> future = completed("Hello");
//        System.out.println(future.isDone());

//        Object o = null;
//        try {
//            o = anyOf().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(o);
//
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        allOf();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Future<Void> runAsync() {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Obj========start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Obj========end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> System.out.println("=========over========"));
    }

    private static void allOf() {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("1========start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("1========end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((v, t) -> System.out.println("========over========")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("2========start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("2========end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + "========over")));
    }

    private static Future<Object> anyOf() {
        return CompletableFuture.anyOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("1========start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("1========end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((v, t) -> System.out.println("========over========")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("2========start");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("2========end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + "========over")));
    }

    private static Future<Void> completed(String data) {
        return CompletableFuture.completedFuture(data).thenAccept(System.out::println);
    }

    private static void supplyAsync() {
        CompletableFuture.supplyAsync(Object::new)
                .thenAcceptAsync(obj -> {
                    try {
                        System.out.println("Obj========start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("Obj========end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).runAfterBoth(CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptAsync(s -> {
                    try {
                        System.out.println("String========start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("String========end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }), () -> System.out.println("======Finish========"));
    }
}
