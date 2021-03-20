package com.vilin.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeTest {

    public static void main(String[] args) {
//        Unsafe unsafe = Unsafe.getUnsafe();

//        Unsafe unsafe = getUnsafe();
//        System.out.println(unsafe);

        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new SyncCounter();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            service.submit(new CounterRunnable(counter, 10000));
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Counter result : " + counter.getCounter());
        System.out.println("Time passed in ms: " + (end - start));
    }

    private static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    interface Counter{

        void increment();

        long getCounter();
    }

    static class StupidCounter implements Counter{

        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return this.counter;
        }
    }

    static class SyncCounter implements Counter{

        private long counter = 0;

        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return this.counter;
        }
    }

    static class LockCounter implements Counter{

        private long counter = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public synchronized void increment() {
            try{
                lock.lock();
                counter++;
            }finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return this.counter;
        }
    }

    static class AtomicCounter implements Counter{

        private AtomicLong counter = new AtomicLong(0);

        private final Lock lock = new ReentrantLock();

        @Override
        public synchronized void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return this.counter.get();
        }
    }

    static class CASCounter implements Counter{

        private volatile long counter = 0;

        private Unsafe unsafe;

        private long offset;

        public CASCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
        }

        @Override
        public synchronized void increment() {
           long current = counter;
           while(!unsafe.compareAndSwapLong(this, offset, current, current + 1)){
               current = counter;
           }
        }

        @Override
        public long getCounter() {
            return this.counter;
        }
    }

    static class CounterRunnable implements Runnable{
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for(int i = 0; i < num; i++){
                counter.increment();
            }
        }
    }
}
