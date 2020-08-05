package com.vilin.juc.c005;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLock {

    ReentrantLock lock = new ReentrantLock();

    public void m1(){

        lock.lock();
        try {
            for (int i = 1; i < 10; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch (InterruptedException e) {
                e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public void m2(){
        lock.lock();
        try{
            System.out.println("m2......");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock t = new T02_ReentrantLock();
        new Thread(t::m1, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2, "t2").start();
    }
}
