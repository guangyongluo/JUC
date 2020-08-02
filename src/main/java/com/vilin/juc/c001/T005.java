package com.vilin.juc.c001;

public class T005 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "m1 started...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m1 stopped...");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "m2");
    }

    public static void main(String[] args) {
        T005 t = new T005();

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}
