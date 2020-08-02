package com.vilin.juc.c001;

public class T001 {
    private int count = 1;
    private Object o = new Object();

    public void m(){
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
