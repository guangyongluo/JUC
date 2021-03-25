package com.vilin.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample2 {

    public static void main(String[] args) {

        final Exchanger<Object> exchanger = new Exchanger<Object>();

        new Thread(){
            @Override
            public void run() {
                Object a = new Object();
                System.out.println("A will send the object " + a);

                try {
                    Object obj = exchanger.exchange(a);
                    System.out.println("A received the object " + obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Object a = new Object();
                System.out.println("B will send the object " + a);

                try {
                    Object obj = exchanger.exchange(a);
                    System.out.println("B received the object " + obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
