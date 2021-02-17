package com.vilin.juc.chapter04;

public class DeamonThread {

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println(Thread.currentThread().getName() + "running");
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName() + "dead");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; // new

        t.setDaemon(true);
        // runnable -> running -> blocked -> dead
        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
