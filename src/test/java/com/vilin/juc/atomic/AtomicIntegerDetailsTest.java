package com.vilin.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDetailsTest {

    @Test
    public void testCreate(){
//        AtomicInteger i = new AtomicInteger();
//        System.out.println(i.get());
//        i = new AtomicInteger(10);
//        System.out.println(i.get());
//
//        i.set(12);
//        System.out.println(i.get());
//
//        i.lazySet(13);
//        System.out.println(i.get());
//
//        AtomicInteger getAndSet = new AtomicInteger(10);
//        int result = getAndSet.getAndAdd(10);
//        System.out.println(result);
//        System.out.println(getAndSet.get());

//        final AtomicInteger getAndSet = new AtomicInteger(0);
//
//
//        new Thread(){
//            @Override
//            public void run() {
//                for(int i = 0; i < 10; i++){
//                    int v = getAndSet.addAndGet(1);
//                    System.out.println(v);
//                }
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                for(int i = 0; i < 10; i++){
//                    int v = getAndSet.addAndGet(1);
//                    System.out.println(v);
//                }
//            }
//        }.start();


        AtomicInteger i = new AtomicInteger(10);

        boolean flag = i.compareAndSet(12, 20);

        System.out.println(i.get());
        System.out.println(flag);
    }
}
