package com.vilin.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample3 {

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 5; i++){
            new Athletes(i, phaser).start();
        }

        new InjureAthletes(5, phaser).start();
    }

    static class InjureAthletes extends Thread{
        private final int no;

        private final Phaser phaser;

        public InjureAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            sport(phaser, no + ": start running.", no + ": end running.");
            sport(phaser, no + ": start bicycle.", no + ": end bicycle.");
            System.out.println("Oh shit, i am injured. i will be exited.");

            phaser.arriveAndDeregister();
        }
    }

    static class Athletes extends Thread{

        private final int no;

        private final Phaser phaser;

        public Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            sport(phaser, no + ": start running.", no + ": end running.");
            sport(phaser, no + ": start bicycle.", no + ": end bicycle.");
            sport(phaser, no + ": start long jump.", no + ": end long jump.");
        }
    }

    private static void sport(Phaser phaser, String x, String x2){
        try {
            System.out.println(x);
            TimeUnit.SECONDS.sleep(random.nextInt(5));
            System.out.println(x2);

            phaser.arriveAndAwaitAdvance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
