package com.vilin.classloader.chapter01;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClinitThreadTest {

    public static void main(String[] args) {
        new Thread(() -> new SimpleObject()).start();
        new Thread(() -> new SimpleObject()).start();
    }

    static class SimpleObject{

        private static AtomicBoolean init = new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName() + " I will be initial");
            while(init.get()){

            }
            System.out.println(Thread.currentThread().getName() + "I am finished initial");
        }
    }
}
