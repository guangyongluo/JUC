package com.vilin.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample6 {

    /**
     * awaitAdvance can decremental the arrived parties?
     * @param args
     */

    public static void main(String[] args) throws InterruptedException {

        final Phaser phaser = new Phaser(6);
//        new Thread(() -> phaser.awaitAdvance(phaser.getPhase())).start();
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(phaser.getArrivedParties());

//        new Thread(() -> phaser.awaitAdvance(10));
//        System.out.println("=========================");

        IntStream.rangeClosed(0, 5).boxed().map(i -> phaser).forEach(AwaitAdvanceTask::new);

    }

    private static class AwaitAdvanceTask extends Thread{

        private final Phaser phaser;

        public AwaitAdvanceTask(Phaser phaser) {
            this.phaser = phaser;
            start();
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " finished work.");
        }
    }
}
