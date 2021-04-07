package com.vilin.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduleExecutorServiceExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

//        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(() -> System.out.println("I will be invoked!"), 2, TimeUnit.SECONDS);

//        System.out.println(schedule.cancel(true));

//        ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> 2, 2, TimeUnit.SECONDS);
//        System.out.println(schedule.get());

//        ScheduledFuture<?> Future = scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("I am running"), 1, 2, TimeUnit.SECONDS);

        final AtomicLong interval = new AtomicLong(0);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if(interval.get() == 0){
                System.out.printf("The first time trigger at %d\n", currentTimeMillis);
            }else{
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


}
