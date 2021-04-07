package com.vilin.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduleExecutorServiceExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        testScheduleWithFixedDelay();
        test1();
    }

    private static void testScheduleWithFixedDelay() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

        final AtomicLong interval = new AtomicLong(0);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger at %d\n", currentTimeMillis);
            } else {
                System.out.printf("The actual spend [%d]\n", currentTimeMillis - interval.get());
            }
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            interval.set(currentTimeMillis);
        }, 1, 2, TimeUnit.SECONDS);
    }


    private static void test1() {
        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
//        scheduledExecutorService.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        System.out.println(scheduledExecutorService.getContinueExistingPeriodicTasksAfterShutdownPolicy());

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("========" + System.currentTimeMillis());
        }, 1, 2, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
        System.out.println("===over===");
    }


    private static void test2(){
        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
//        scheduledExecutorService.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        System.out.println(scheduledExecutorService.getExecuteExistingDelayedTasksAfterShutdownPolicy());

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("========" + System.currentTimeMillis());
        }, 1, 2, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();
        System.out.println("===over===");
    }
}
