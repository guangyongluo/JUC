package com.vilin.juc.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {

    //The initValue is ture indicated the lock have be get.
    //The initValue is false indicated the lock is free (other thread can get this.).
    private boolean initValue;

    private Collection<Thread> blockThreadCollection = new ArrayList<Thread>();

    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        this.initValue = true;
        blockThreadCollection.remove(Thread.currentThread());
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if(mills <= 0){
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while(initValue){

            if(hasRemaining <= 0){
                throw new TimeOutException("Time out");
            }
            blockThreadCollection.add(Thread.currentThread());
            this.wait(hasRemaining);

            hasRemaining = endTime - System.currentTimeMillis();

        }

        this.initValue = true;
        blockThreadCollection.remove(Thread.currentThread());
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if(Thread.currentThread() == currentThread){
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " released the lock monitor.")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThreads() {
        return Collections.unmodifiableCollection(blockThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockThreadCollection.size();
    }
}
