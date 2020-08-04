package com.vilin.juc.c003;

import com.vilin.juc.c002.T001;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {

    private AtomicInteger count = new AtomicInteger(0);

    public void m(){
        for(int i = 0; i < 10000; i++){
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<Thread>();

        for(int i = 0; i < 10; i++){
            threads.add(new Thread(t::m, "Thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
