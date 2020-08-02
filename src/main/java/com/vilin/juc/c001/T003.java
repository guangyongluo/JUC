package com.vilin.juc.c001;

public class T003 {
    private int count = 1;

    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);

    }
}
