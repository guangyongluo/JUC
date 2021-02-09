package com.vilin.juc.chapter02;

public class TicketWindowRunnable implements Runnable{

    private final int MAX = 50;

    private int index = 0;

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
        }
    }
}
