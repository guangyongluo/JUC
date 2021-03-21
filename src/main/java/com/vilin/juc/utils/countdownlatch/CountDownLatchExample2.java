package com.vilin.juc.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample2 {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        Thread mainThread = Thread.currentThread();

        new Thread(){
            @Override
            public void run() {
                System.out.println("Do some initial working.");
                try {
                    Thread.sleep(1000);
                    latch.await();
                    System.out.println("do other working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println("async prepare for some data");
                try {
                    Thread.sleep(2000);
                    System.out.println("data prepare done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    latch.await();
                    System.out.println("release.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
