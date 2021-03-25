package com.vilin.juc.utils.condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionExample3 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition PRODUCE_CONDITON = lock.newCondition();

    private final static Condition CONSUME_CONDITON = lock.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) {

        IntStream.range(0,6).boxed().forEach(ConditionExample3::beginProduce);
        IntStream.range(0,13).boxed().forEach(ConditionExample3::beginConsume);

//        while (true){
//            sleep(5);
//            System.out.println("=============================================");
//            System.out.println("PRODUCE_CONDITON.getWaitQueueLength(PRODUCE_CONDITON) => " + lock.getWaitQueueLength(PRODUCE_CONDITON));
//            System.out.println("CONSUME_CONDITON.getWaitQueueLength(CONSUME_CONDITON) => " + lock.getWaitQueueLength(CONSUME_CONDITON));
//            System.out.println("PRODUCE_CONDITON.hasWaiters(PRODUCE_CONDITON) => " + lock.hasWaiters(PRODUCE_CONDITON));
//            System.out.println("PRODUCE_CONDITON.hasWaiters(PRODUCE_CONDITON) => " + lock.hasWaiters(PRODUCE_CONDITON));
//        }
    }

    private static void beginProduce(int i){
        new Thread(() -> {
            for(;;){
                produce();
                sleep(1);
            }
        }, "P-" + i).start();
    }

    private static void beginConsume(int i){
        new Thread(() -> {
            for(;;){
                consume();
                sleep(1);
            }
        }, "C-" + i).start();
    }

    private static void produce(){
        try {
            lock.lock();
            while(TIMESTAMP_POOL.size() >= MAX_CAPACITY){
                PRODUCE_CONDITON.await();
            }

            System.out.println("PRODUCE_CONDITON.getWaitQueueLength(PRODUCE_CONDITON) => " + lock.getWaitQueueLength(PRODUCE_CONDITON));
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            TIMESTAMP_POOL.addLast(value);

            CONSUME_CONDITON.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private static void consume(){
        try {
            lock.lock();
            while(TIMESTAMP_POOL.isEmpty()){
                CONSUME_CONDITON.await();
            }

            long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + value);

            PRODUCE_CONDITON.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
