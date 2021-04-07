package com.vilin.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceExample5 {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);

        executorService.execute(() -> System.out.println("I will be process because of triggered the execute."));
        executorService.getQueue().add(() -> System.out.println("I am added directly into the queue"));

    }
}
