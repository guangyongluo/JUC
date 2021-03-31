package com.vilin.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample3 {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

                System.out.println("T1 finished.");
            }
        };

        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    semaphore.acquireUninterruptibly();
//                    TimeUnit.SECONDS.sleep(5);
                } finally {
                    semaphore.release();
                }

                System.out.println("T2 finished.");
            }
        };

        t2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();
    }
}
