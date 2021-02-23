package com.vilin.juc.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is Working...").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3")
                .forEach(name ->
                        new Thread(() -> {
                            try {
                                booleanLock.lock(10);
                                Optional.of(Thread.currentThread().getName() + " have the lock monitor")
                                        .ifPresent(System.out::println);
                                work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (Lock.TimeOutException e) {
                                Optional.of(Thread.currentThread().getName() + " time out.")
                                        .ifPresent(System.out::println);
                                e.printStackTrace();
                            } finally {
                                booleanLock.unlock();
                            }

                        }, name).start()
                );

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        booleanLock.unlock();
    }
}
