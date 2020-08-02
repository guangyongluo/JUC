package com.vilin.juc.c001;

public class T004 {

    private static int count = 1;

    public static synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);

    }
}
