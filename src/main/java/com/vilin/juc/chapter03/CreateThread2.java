package com.vilin.juc.chapter03;

import java.util.Arrays;

public class CreateThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        System.out.println("当前执行线程main的名字：" + Thread.currentThread().getName());

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("当前执行线程main的ThreadGroup的名字：" + threadGroup.getName());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

        Arrays.asList(threads).forEach(System.out::println);
    }
}
