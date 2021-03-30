package com.vilin.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PhaserExample7 {

    public static void main(String[] args) throws InterruptedException {
        final Phaser phaser = new Phaser(3);

//        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
//        thread.start();
//
//        System.out.println("==================================");
//        TimeUnit.SECONDS.sleep(10);
//
//        thread.interrupt();
//        System.out.println("=======thread.interrupt===========");

        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(phaser.getPhase(), 10, TimeUnit.SECONDS);
                System.out.println("********************");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        System.out.println("=====================================");
        TimeUnit.SECONDS.sleep(10);
//        thread.interrupt();
        System.out.println("=======thread.interrupt==============");
    }
}
