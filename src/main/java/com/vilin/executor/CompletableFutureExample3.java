package com.vilin.executor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureExample3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
//        future.whenComplete((v, t) -> System.out.println("done " + v));
//        CompletableFuture<String> future1 = future.whenCompleteAsync((v, t) -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("=====over=====");
//        });
//        System.out.println("==============");
//        System.out.println(future1.get());

//        CompletableFuture<Integer> future2 = future.thenApply(String::length);
//        CompletableFuture<Integer> future3 = future.thenApplyAsync(s -> {
//            System.out.println("===============");
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return s.length();
//        });
//        System.out.println("*******************");
//        System.out.println(future3.get());

//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
//                (Supplier<String>)() -> {
//                    throw new RuntimeException("not get the data");
//                }).handleAsync((s, t) -> {
//            Optional.of(t).ifPresent(e -> System.out.println("Error"));
//            return s == null ? 0 : s.length();
//        });
//
//        System.out.println(future.get());

//        future.thenAccept(System.out::println);
        future.thenRun(() -> System.out.println("done")).thenRunAsync(() -> System.out.println("done"));
    }
}
