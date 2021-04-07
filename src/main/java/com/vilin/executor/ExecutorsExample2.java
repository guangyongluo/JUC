package com.vilin.executor;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorsExample2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();
//        Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println("Thread" + Thread.currentThread().getName());
                    sleep(100);
                    return "Task-" + i;
                }
        ).collect(Collectors.toList());

        try {
            executorService.invokeAll(callableList).stream().map(future -> {
                try {
                    return future.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
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
