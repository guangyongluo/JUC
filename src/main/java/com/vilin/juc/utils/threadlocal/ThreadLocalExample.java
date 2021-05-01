package com.vilin.juc.utils.threadlocal;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadLocalExample {

    public static void main(String[] args) {
        //创建ThreadLocal实例
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        //创建是个线程，使用local
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
                    try {
                        //每个线程都会设置threadLocal，但是彼此之间的数据是独立的
                        threadLocal.set(i);
                        System.out.println(Thread.currentThread() + " set i " + threadLocal.get());

                        TimeUnit.SECONDS.sleep(1);

                        System.out.println(Thread.currentThread() + " set i " + threadLocal.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );

        
    }
}
