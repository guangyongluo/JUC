package com.vilin.juc.c002;

import java.util.ArrayList;
import java.util.List;

public class T001 {
    private volatile int count = 0;
    public synchronized void m(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }

    public static void main(String[] args) {
        T001 t = new T001();
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
