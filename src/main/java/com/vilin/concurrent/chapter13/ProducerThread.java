package com.vilin.concurrent.chapter13;

import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread{

    private final MessageQueue messageQuueue;

    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQuueue, int seq){
        super("PRODUCER-" + seq);
        this.messageQuueue = messageQuueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = new Message("Message-" + counter.getAndIncrement());
                messageQuueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
