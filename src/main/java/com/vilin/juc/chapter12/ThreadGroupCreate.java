package com.vilin.juc.chapter12;

import java.util.Arrays;

public class ThreadGroupCreate {

    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());

        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1"){
            @Override
            public void run() {
                try {
                    Thread.sleep(10_000);
                    System.out.println(getThreadGroup().getName());
                    System.out.println(getThreadGroup().getParent());
                    System.out.println(getThreadGroup().getParent().activeCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();

//        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
//        System.out.println(tg2.getName());
//        System.out.println(tg2.getParent());

        ThreadGroup tg3 = new ThreadGroup("TG3");
        Thread t3 = new Thread(tg3, "T2"){
            @Override
            public void run() {
                System.out.println(tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()];
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
            }
        };

        t3.start();
    }
}
