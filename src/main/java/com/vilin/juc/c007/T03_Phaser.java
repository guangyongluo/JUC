package com.vilin.juc.c007;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleToIntFunction;

public class T03_Phaser {

    private static Random random = new Random();

    private static MarriagePhaser phaser = new MarriagePhaser();


    public static class Person implements Runnable{
        private String name;

        public Person(String name){
            this.name = name;
        }

        public void arrive(){
            milliSpleep(random.nextInt(1000));
            System.out.printf("%s 到达现场\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat(){
            milliSpleep(random.nextInt(1000));
            System.out.printf("%s 吃完！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            milliSpleep(random.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            if(name.equals("新郎") || name.equals("新粮")){
                milliSpleep(random.nextInt(1000));
                System.out.printf("%s 洞房\n", name);
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }

        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    public static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties){
            switch (phase){
                case 0 :
                    System.out.println("所有人到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1 :
                    System.out.println("所有人都吃完！" + registeredParties);
                    System.out.println();
                    return false;
                case 2 :
                    System.out.println("所有人都离开！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("结婚结束，新郎新娘拥抱" + registeredParties);
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }


    private static void  milliSpleep(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        phaser.bulkRegister(7);

        for(int i = 0; i < 5; i++){
            new Thread(new Person("p" + i)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新粮")).start();
    }
}
