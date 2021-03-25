package com.vilin.juc.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample2 {

    private final static ReentrantLock lock = new ReentrantLock(true);

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static void buildData(){
        try {
            lock.lock();  //synchronized start  #monitor enter

            TimeUnit.SECONDS.sleep(1);
            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //synchronized  #monitor end
        }
    }

    private static void useData(){
        try {
            lock.lock();

            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            for(;;){
                buildData();
            }
        }).start();

        new Thread(() -> {
            for (;;){
                useData();
            }
        }).start();
    }
}
