package com.vilin.juc.chapter02;

public class TicketWindow extends Thread{

    private final String name;

    private final static int MAX = 50;

    private static int index = 1;

    public TicketWindow(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("柜台：" + name + "当前的号码是：" + (index++));
        }
    }
}
