package com.vilin.juc.c005;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock {

    ReentrantLock lock = new ReentrantLock();

    public void m1(){

        lock.lock();
        try {
            for (int i = 0; i < 3; i++){
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
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(locked){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock t = new T03_ReentrantLock();
        new Thread(t::m1, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2, "t2").start();
    }
}
