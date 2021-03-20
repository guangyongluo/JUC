package com.vilin.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");

        TestMe me = new TestMe();
        for(int i = 0; i < 2; i++){
            new Thread(){
                @Override
                public void run() {
                    final int MAX = 20;
                    for(int i = 0; i < MAX; i++){
                        int value = updater.incrementAndGet(me);
                        System.out.println(Thread.currentThread().getName() + "=>" + value);
                    }
                }
            }.start();
        }
    }

    static class TestMe{
        volatile int i;
    }

}


