package com.vilin.juc.chapter08;

public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread("T1"){
            @Override
            public void run() {
                while (true){
                    deadLock.m1();
                }
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                while (true){
                    otherService.s2();
                }
            }
        }.start();
    }
}
