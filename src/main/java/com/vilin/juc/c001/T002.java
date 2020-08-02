package com.vilin.juc.c001;

public class T002 {

    private int count = 1;

    public void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
