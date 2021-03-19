package com.vilin.classloader.chapter05;

public class SimpleClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.vilin.classloader.chapter05.SimpleObject");

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
    }
}
