package com.vilin.juc.c008;

import java.lang.ref.SoftReference;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class T01_SoftReference {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());
    }
}
