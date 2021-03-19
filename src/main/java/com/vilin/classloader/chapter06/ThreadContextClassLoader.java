package com.vilin.classloader.chapter06;

import com.vilin.classloader.chapter03.MyClassLoader;

public class ThreadContextClassLoader {

    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
