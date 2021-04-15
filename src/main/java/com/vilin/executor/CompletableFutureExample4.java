package com.vilin.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample4 {

    public static void main(String[] args) throws InterruptedException {
//        thenAcceptBoth();
//        acceptEither();
//        runAfterBoth();
//        runAfterEither();
//        combine();
        compose();
        Thread.currentThread().join();
    }

    private static void thenAcceptBoth(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the supplyAsync");
            sleep(5);
            System.out.println("End the supplyAsync");
            return "thenAcceptBoth";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the supplyAsync");
            sleep(5);
            System.out.println("End the supplyAsync");
            return 1000;
        }), (s, i) -> System.out.println(s + "--" + i));
    }

    private static void acceptEither(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the acceptEither-1");
            sleep(5);
            System.out.println("End the acceptEither-1");
            return "acceptEither-1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the acceptEither-2");
            sleep(5);
            System.out.println("End the acceptEither-2");
            return "acceptEither-2";
        }), System.out::println);
    }

    private static void runAfterBoth(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the runAfterBoth-1");
            sleep(5);
            System.out.println("End the runAfterBoth-1");
            return "runAfterBoth-1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the runAfterBoth-2");
            sleep(5);
            System.out.println("End the runAfterBoth-2");
            return 100;
        }), () -> System.out.println("DONE"));
    }

    private static void runAfterEither(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the runAfterEither-1");
            sleep(5);
            System.out.println("End the runAfterEither-1");
            return "runAfterEither-1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the runAfterEither-2");
            sleep(3);
            System.out.println("End the runAfterEither-2");
            return 100;
        }), () -> System.out.println("DONE"));
    }

    private static void combine(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the combine-1");
            sleep(5);
            System.out.println("End the combine-1");
            return "combine-1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the combine-2");
            sleep(3);
            System.out.println("End the combine-2");
            return 100;
        }), (s, i) -> s.length() > i).whenComplete((v, t) -> System.out.println(v));
    }

    private static void compose(){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the compose-1");
            sleep(5);
            System.out.println("End the compose-1");
            return "compose-1";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Start the compose-2");
            sleep(3);
            System.out.println("End the compose-2");
            return s.length();
        })).thenAccept(System.out::println);
    }

    private static void sleep(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
