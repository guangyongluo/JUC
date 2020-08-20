package com.vilin.juc.c007;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T04_ReadWriteLock {

    private static Lock lock = new ReentrantLock();

    private static int value;

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int value){
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write over!");
            value = value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable readR = () -> read(readLock);

        Runnable writeR = () -> write(writeLock, new Random().nextInt());

        for(int i = 0; i < 18; i++){
            new Thread(readR).start();
        }

        for(int i = 0; i < 2; i++){
            new Thread(writeR).start();
        }

    }
}
