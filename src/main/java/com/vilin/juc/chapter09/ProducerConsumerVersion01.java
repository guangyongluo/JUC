package com.vilin.juc.chapter09;

public class ProducerConsumerVersion01 {

    private int i = 0;

    private final Object LOCK = new Object();

    public void produce(){
        synchronized (LOCK){
            System.out.println("P->" + (i++));
        }
    }

    public void consume(){
        synchronized (LOCK){
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion01 producerConsumerVersion01 = new ProducerConsumerVersion01();

        new Thread("P"){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion01.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while (true){
                    producerConsumerVersion01.consume();
                }
            }
        }.start();
    }
}
