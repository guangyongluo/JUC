package com.vilin.juc.c003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T01_AtomicVsSynVsLongAdder {

    private static Long count1 = 0l;
    private static AtomicLong count2 = new AtomicLong(0);
    private static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        Thread[] threads = new Thread[1000];

        Object lock = new Object();
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(() ->{
                for(int j = 0; j < 100000; j++){
                    synchronized (lock) {
                        count1++;
                    }
                }
            });
        }

        long start = System.currentTimeMillis();

        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("synchronized : " + count1.toString() + " time : " + (end - start));

        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(() ->{
                for(int j = 0; j < 100000; j++){
                    count2.incrementAndGet();
                }
            });
        }

        start = System.currentTimeMillis();

        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("AtomicLong : " + count2.toString() + " time : " + (end - start));



        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(() ->{
                for(int j = 0; j < 100000; j++){
                    count3.increment();
                }
            });
        }

        start = System.currentTimeMillis();

        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println("LongAdder : " + count3.toString() + " time : " + (end - start));

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count1 = " + count1);
        System.out.println("count2 = " + count2);
        System.out.println("count3 = " + count3);
    }
}
