package com.vilin.juc.c005;

import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock {
    public synchronized void m1(){
        for (int i = 1; i < 10; i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);

            if(i == 5){
                m2();
            }
        }
    }

    public synchronized void m2(){
        System.out.println("m2......");
    }

    public static void main(String[] args) {
        T01_ReentrantLock t = new T01_ReentrantLock();
        new Thread(t::m1, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2, "t2").start();
    }
}
