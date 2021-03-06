package com.vilin.juc.chapter02;

public class Bank2 {

    private final static int MAX = 50;

    public static void main(String[] args) {

//        TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        Runnable ticketWindow = () -> {
            int index = 1;
            while (index <= MAX){
                System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
            }
        };

        Thread windowThread1 = new Thread(ticketWindow, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
