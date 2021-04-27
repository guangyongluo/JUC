package com.vilin.juc.utils.future;

import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done.");
        });
        future.get();

        FutureService<String, Integer> service1 = FutureService.newService();
        Future<Integer> future1 = service1.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "Hello");
        System.out.println(future1.get());
    }
}
