package com.vilin.juc.chapter06;

public class ThreadInterrupt {

    private final static Object MONITOR = new Object();

    public static void main(String[] args) {
//        Thread t = new Thread(){
//            @Override
//            public void run(){
//                while (true){
//                    synchronized (MONITOR){
//                        try {
//                            MONITOR.wait(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            System.out.println(isInterrupted());
//                        }
//                    }
//
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        System.out.println("收到打断信号");
//                        e.printStackTrace();
//                    }
//                    System.out.println(">>" + this.isInterrupted());
//                }
//            }
//        };
//
//        t.start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(t.isInterrupted());
//        t.interrupt();
//        System.out.println(t.isInterrupted());

//        Thread t = new Thread(() -> {
//            while(true){
//                synchronized (MONITOR){
//                    try {
//                        MONITOR.wait(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        System.out.println(Thread.interrupted());
//                    }
//                }
//            }
//        });

        Thread t = new Thread(){
            @Override
            public void run() {
                while (true){

                }
            }
        };

        t.start();

        Thread main = Thread.currentThread();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

//                t.interrupt();
                main.interrupt();
                System.out.println("interrupt");
            }
        };

        t2.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
