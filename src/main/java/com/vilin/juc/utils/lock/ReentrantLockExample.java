package com.vilin.juc.utils.lock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample {

    private static final Lock LOCK = new ReentrantLock();

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
//        IntStream.range(0, 2).forEach(i -> new Thread(){
//            @Override
//            public void run() {
//                needLock();
//                needLockBySync();
//            }
//        }.start());

//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                testUnInterruptibly();
//            }
//        };
//
//        thread1.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                testUnInterruptibly();
//            }
//        };
//
//        thread2.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        thread2.interrupt();
//        System.out.println("================");

//        Thread t1 = new Thread(() -> testTryLock());
//        t1.start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Thread t2 = new Thread(() -> testTryLock());
//        t2.start();

        Thread t1 = new Thread(() -> testInterruptibly());
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> testInterruptibly());
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Optional.of(lock.getQueueLength()).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThreads()).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThread(t1)).ifPresent(System.out::println);
        Optional.of(lock.hasQueuedThread(t2)).ifPresent(System.out::println);
        Optional.of(lock.isLocked()).ifPresent(System.out::println);


    }

    public static void testTryLock(){
        if(LOCK.tryLock()){
            try {
                Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
                while (true) {

                }
            }finally {
                LOCK.unlock();
            }
        }else {
            Optional.of("The thread-" + Thread.currentThread().getName() + " can't get lock.").ifPresent(System.out::println);
        }
    }

    public static void testInterruptibly(){
        try{
            lock.lockInterruptibly();
            Optional.of(Thread.currentThread().getName() + ":" + lock.getHoldCount()).ifPresent(System.out::println);
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            while (true){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void testUnInterruptibly(){
        try{
            LOCK.lockInterruptibly();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            while (true){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public static void needLock(){
        try {
            LOCK.lock();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public static void needLockBySync(){
        synchronized (ReentrantLockExample.class){
            try {
                Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
