package com.vilin.juc.c001;

import java.util.concurrent.TimeUnit;

public class Account {

    private String name;
    private double balance;

    public synchronized void set(String name, double balance){
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public synchronized double get(String name){
        if(this.name == name){
            return balance;
        }else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> {
            a.set("zhangsan", 1000);
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.get("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.get("zhangsan"));
    }
}
