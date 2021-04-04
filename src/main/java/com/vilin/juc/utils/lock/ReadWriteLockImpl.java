package com.vilin.juc.utils.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockImpl implements ReadWriteLock {

    //定义对象锁
    private final Object MUTEX = new Object();

    //当前有多少个线程正在写入
    private int writingWriters = 0;

    //当前有多少个线程正在等待写入
    private int waitingWriters = 0;

    //当前有多少个线程正在read
    private int readingReaders = 0;

    //read和write的偏好设置
    private boolean preferWriter;

    //默认情况下preferWriter为true
    public ReadWriteLockImpl(){
        this(true);
    }

    //构造ReadWriteLockImpl并且传入preferWriter
    public ReadWriteLockImpl(boolean preferWriter){
        this.preferWriter = preferWriter;
    }

    //创建read lock
    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    //创建write lock
    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    //使写入线程的数量增加
    public void incrementWritingWriters(){
        this.writingWriters++;
    }

    //使等待写入的线程数量增加
    public void incrementWaitingWriters(){
        this.waitingWriters++;
    }

    //使读线程的数量增加
    public void incrementReadingReaders(){
        this.readingReaders++;
    }

    //使写线程的数量减少
    public void decrementWritingWriters(){
        this.writingWriters--;
    }

    //使等待获取写入锁的数量减少
    public void decrementWaitingWriters(){
        this.waitingWriters--;
    }

    //使读取线程的数量减少
    public void decrementReadingReaders(){
        this.readingReaders--;
    }

    //获取当前有多少个线程正在进行写操作
    public int getWritingWriters(){
        return this.writingWriters;
    }

    //获取当前有多少个线程正在等待获取写入锁
    public int getWaitingWriters(){
        return this.waitingWriters;
    }

    //获取当前多少个线程正在进行读操作
    public int getReadingReaders(){
        return this.readingReaders;
    }

    //获取对象锁
    public Object getMutex(){
        return this.MUTEX;
    }

    //获取当前是否偏向写锁
    public boolean getPreferWriter(){
        return this.preferWriter;
    }

    //设置写偏好
    public void changePrefer(boolean preferWriter){
        this.preferWriter = preferWriter;
    }
}
