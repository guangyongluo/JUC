package com.vilin.juc.utils.active.common;

import java.util.LinkedList;

public class ActiveMessageQueue {

    private final LinkedList<ActiveMessage> messages = new LinkedList<>();

    public ActiveMessageQueue(){
        new ActiveDaemonThread(this).start();
    }

    public void offer(ActiveMessage activeMessage){
        synchronized (this){
            messages.addLast(activeMessage);
            this.notify();
        }
    }

    public ActiveMessage take(){
        synchronized (this){
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messages.removeFirst();
        }
    }
}
