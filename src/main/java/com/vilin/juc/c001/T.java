package com.vilin.juc.c001;

import java.util.concurrent.TimeUnit;

public class T {

    public synchronized void m(){
        System.out.println("m started");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m stopped");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T{

    public synchronized void m(){
        System.out.println("child m started");
        super.m();
        System.out.println("child m stopped");
    }
}
