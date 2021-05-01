package com.vilin.juc.utils.active.common;

public class ActiveDaemonThread extends Thread {

    private final ActiveMessageQueue queue;

    public ActiveDaemonThread(ActiveMessageQueue queue) {
        super("ActiveDaemonThread");
        this.queue = queue;
        //ActiveDaemonThread为守护线程
        setDaemon(true);
    }

    @Override
    public void run() {
        for (; ; ){
            ActiveMessage activeMessage = this.queue.take();
            activeMessage.execute();
        }
    }
}
