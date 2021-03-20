package com.vilin.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanFlag {

    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                while(flag.get()){
                    try {
                        Thread.sleep(1000);
                        System.out.println("I am working.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("I am finished.");
            }
        }.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(){
            @Override
            public void run() {
                flag.set(false);
            }
        }.start();
    }
}
