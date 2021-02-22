package com.vilin.juc.chapter09;

public class ProducerConsumerVersion02 {

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
                System.out.println("P->" + ++i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume(){
        synchronized (LOCK){
            if (isProduced){
                System.out.println("C->" + i);
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
        ProducerConsumerVersion02 producerConsumerVersion02 = new ProducerConsumerVersion02();

        new Thread("P"){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion02.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion02.consume();
                }
            }
        }.start();
    }
}
