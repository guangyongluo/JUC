package com.vilin.juc.utils.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() {
        //使用Mutex作为锁
        synchronized (readWriteLock.getMutex()){
            //若此时有线程在进行写操作，或者有写线程在等待并且偏向写的标识为true时，就会无法获得读锁，只能被挂起
            while(readWriteLock.getWritingWriters() > 0 || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)){
                try {
                    readWriteLock.getMutex().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //成功获得读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        //使用Mutex作为锁，并且进行同步
        synchronized (readWriteLock.getMutex()){
            //释放锁的过程就是使得当前reading的数量减一
            //将preferWriter设置为true,可以使得writer线程获得更多的机会
            //通知唤醒与Mutex关联monitor waitset中的线程
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMutex().notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
