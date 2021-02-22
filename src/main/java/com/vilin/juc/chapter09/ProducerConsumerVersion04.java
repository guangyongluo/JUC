package com.vilin.juc.chapter09;

import java.util.stream.Stream;

public class ProducerConsumerVersion04 {

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce(){
        synchronized (LOCK){

            while (isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            System.out.println(Thread.currentThread().getName() + "->" + i);
            LOCK.notifyAll();
            isProduced = true;

        }
    }

    public void consume(){
        synchronized (LOCK){
            while (!isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "->" + i);
            LOCK.notifyAll();
            isProduced = false;

        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion04 producerConsumerVersion04 = new ProducerConsumerVersion04();

        Stream.of("P1", "P2", "P3").forEach(n -> new Thread(n){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion04.produce();
                }
            }
        }.start());

        Stream.of("C1", "C2", "C3").forEach(n -> new Thread(n){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion04.consume();
                }
            }
        }.start());
    }
}
