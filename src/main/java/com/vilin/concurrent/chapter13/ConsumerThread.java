package com.vilin.concurrent.chapter13;

public class ConsumerThread extends Thread{

    private final MessageQueue messageQuueue;

    public ConsumerThread(MessageQueue messageQuueue, int seq){
        super("CONSUMER-" + seq);
        this.messageQuueue = messageQuueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = messageQuueue.get();
                System.out.println(Thread.currentThread().getName() + " get message " + message.getData());
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
