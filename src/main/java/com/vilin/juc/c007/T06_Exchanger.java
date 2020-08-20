package com.vilin.juc.c007;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class T06_Exchanger {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() -> {
            String s = "T1";
            try{
                s = exchanger.exchange(s, 1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "T1").start();

//        new Thread(() -> {
//            String s = "T2";
//            try{
//                s = exchanger.exchange(s);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " " + s);
//        }, "T2").start();
    }

}
