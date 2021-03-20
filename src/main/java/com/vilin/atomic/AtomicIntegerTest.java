package com.vilin.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    /**
     * 1. 内存可见性；
     * 2. 顺序性；
     *
     * 不能保证原子性
     */
    private static volatile int value;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet());

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//                int x = 0;
//                while (x < 5000) {
//                    int temp = value;
//                    set.add(temp);
//                    System.out.println(Thread.currentThread().getName() + " : " + temp);
//                    value += 1;
//                    x++;
//
//                    /**
//                     * value++
//                     * 1. get value from main memory to local memory;
//                     * 2. add 1 => x;
//                     * 3. assign the value to x;
//                     * 4. flush to main memory.
//                     */
//                }
//            }
//        };
//
//        Thread t2 = new Thread() {
//            @Override
//            public void run() {
//                int x = 0;
//                while (x < 5000) {
//                    int temp = value;
//                    set.add(temp);
//                    System.out.println(Thread.currentThread().getName() + " : " + temp);
//                    value += 1;
//                    x++;
//                }
//            }
//        };
//
//        Thread t3 = new Thread() {
//            @Override
//            public void run() {
//                int x = 0;
//                while (x < 5000) {
//                    int temp = value;
//                    set.add(temp);
//                    System.out.println(Thread.currentThread().getName() + " : " + temp);
//                    value += 1;
//                    x++;
//                }
//            }
//        };

        AtomicInteger atomicInteger = new AtomicInteger();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 5000) {
                    int v = atomicInteger.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + " : " + v);
                    value += 1;
                    x++;

                    /**
                     * value++
                     * 1. get value from main memory to local memory;
                     * 2. add 1 => x;
                     * 3. assign the value to x;
                     * 4. flush to main memory.
                     */
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 5000) {
                    int v = atomicInteger.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + " : " + v);
                    value += 1;
                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 5000) {
                    int v = atomicInteger.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName() + " : " + v);
                    value += 1;
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(set.size());

    }
}
