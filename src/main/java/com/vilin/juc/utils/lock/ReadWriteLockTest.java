package com.vilin.juc.utils.lock;

import static java.lang.Thread.currentThread;

public class ReadWriteLockTest {

    //This is the example for read write lock
    private final static String TEXT = "thisistheexampleforreadwritelock";

    public static void main(String[] args) {
        //定义共享数据
        final ShareData shareData = new ShareData(50);

        //创建两个线程及逆行数据写操作
        for(int i = 0; i < 2; i++){
            new Thread(() -> {
                for(int index = 0; index < TEXT.length(); index++){
                    char c = TEXT.charAt(index);
                    shareData.write(c);
                    System.out.println(currentThread() + " write " + c);
                }
            }).start();
        }

        //创建10个线程进行数据读操作
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                while (true) {
                    System.out.println(currentThread() + " read " + new String(shareData.read()));
                }
            }).start();
        }
    }
}
