package com.vilin.juc.utils.latch;

import java.util.concurrent.TimeUnit;

public class LatchTest {

    public static void main(String[] args) {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Gavin", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "Dillon", "Bicycle").start();

        try {
            latch.await(TimeUnit.SECONDS, 5);
            System.out.println("== all of programmer arrived ==");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
        }
    }
}
