package com.vilin.juc.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample1 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all of finished.");
            }
        });

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(50);
                    System.out.println("T1 finished.");
                    cyclicBarrier.await();
                    System.out.println("T1 the other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("T2 finished.");
                    cyclicBarrier.await();
                    System.out.println("T2 the other thread finished too.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while(true) {
            System.out.println(cyclicBarrier.getNumberWaiting());
            System.out.println(cyclicBarrier.getParties());
            System.out.println(cyclicBarrier.isBroken());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
