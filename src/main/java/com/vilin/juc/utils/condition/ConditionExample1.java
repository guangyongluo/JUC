package com.vilin.juc.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample1 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static void buildData(){
        try {
            lock.lock();  //synchronized start  #monitor enter
            while(noUse){
                condition.await(); //monitor.wait()
            }
            TimeUnit.SECONDS.sleep(1);
            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);

            noUse = true;
            condition.signal(); // monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //synchronized  #monitor end
        }
    }

    private static void useData(){
        try {
            lock.lock();
            while(!noUse){
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            noUse = false;
            condition.signal();
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
