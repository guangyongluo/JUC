package com.vilin.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException{
        boolean success = value.compareAndSet(0, 1);
        System.out.println(value.get());
        if(!success){
            throw new GetLockException("Get the Lock failed");
        }else {
            lockedThread = Thread.currentThread();
        }
    }

    public void unlock(){
        if(0 == value.get()){
            System.out.println(value.get());
            return;
        }

        if(lockedThread == Thread.currentThread()) {
            value.compareAndSet(1, 0);
        }
    }
}
