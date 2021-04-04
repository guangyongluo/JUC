package com.vilin.juc.utils.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() {
        synchronized (readWriteLock.getMutex()){
            try{
                //首先使等待获取写入锁的数字加一
                readWriteLock.incrementWaitingWriters();
                //如果此时有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
                while (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0){
                    readWriteLock.getMutex().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //成功获取到了写入锁，使得等待获取写入锁的计数器减一
                this.readWriteLock.decrementWaitingWriters();
            }
            //将正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();
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
        synchronized (readWriteLock.getMutex()){
            //减少正在写入锁的线程计数器
            readWriteLock.decrementWritingWriters();
            //将偏好状态修改为false，可以使的读锁被最快速的获得
            readWriteLock.changePrefer(false);
            //通知唤醒其他在Mutex monitor waitset中的线程
            readWriteLock.getMutex().notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
