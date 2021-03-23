package com.vilin.juc.utils.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2 {

    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 2; i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " in");
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " get the semaphore.");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                    System.out.println(Thread.currentThread().getName() + " out");
                }
            }.start();
        }

        while(true){
            System.out.println("AP -> " + semaphore.availablePermits());
            System.out.println("QL -> " + semaphore.getQueueLength());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
