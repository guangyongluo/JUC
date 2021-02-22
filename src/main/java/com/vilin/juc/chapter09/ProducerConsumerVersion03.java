package com.vilin.juc.chapter09;

import java.util.stream.Stream;

public class ProducerConsumerVersion03 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce(){
        synchronized (LOCK){
            if (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                i++;
                System.out.println(Thread.currentThread().getName() + "->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume(){
        synchronized (LOCK){
            if (isProduced){
                System.out.println(Thread.currentThread().getName() + "->" + i);
                LOCK.notify();
                isProduced = false;
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion03 producerConsumerVersion03 = new ProducerConsumerVersion03();

        Stream.of("P1", "P2").forEach(n -> new Thread(n){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion03.produce();
                }
            }
        }.start());

        Stream.of("C1", "C2").forEach(n -> new Thread(n){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion03.consume();
                }
            }
        }.start());
    }
}
