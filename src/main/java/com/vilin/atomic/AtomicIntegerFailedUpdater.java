package com.vilin.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFailedUpdater {

    private volatile int i;

    private AtomicIntegerFieldUpdater<AtomicIntegerFailedUpdater> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFailedUpdater.class, "i");

    public void update(int newValue){
        updater.compareAndSet(this, i, newValue);
    }

    public int get(){
        return i;
    }

    public static void main(String[] args) {
        AtomicIntegerFailedUpdater test = new AtomicIntegerFailedUpdater();
        test.update(10);
        System.out.println(test.get());


    }
}
