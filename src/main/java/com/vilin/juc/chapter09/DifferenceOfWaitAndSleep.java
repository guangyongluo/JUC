package com.vilin.juc.chapter09;

public class DifferenceOfWaitAndSleep {

    public final static Object LOCK = new Object();

    public static void m1(){
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m2(){
        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        DifferenceOfWaitAndSleep.m2();
    }
}
