package com.vilin.juc.c005;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock extends Thread{

    private static ReentrantLock lock = new ReentrantLock(true);

    public void run(){
        for(int i = 0; i < 100; i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock t = new T05_ReentrantLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }
}
