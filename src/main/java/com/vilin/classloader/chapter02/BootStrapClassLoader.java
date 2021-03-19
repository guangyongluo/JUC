package com.vilin.classloader.chapter02;

public class BootStrapClassLoader {

    public static void main(String[] args) {

        System.out.println("========BootStrap========");
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("========Extension========");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("========ClassLoader========");
        try {
            Class<?> clazz = Class.forName("com.vilin.classloader.chapter02.BootStrapClassLoader");
            System.out.println(clazz.getClassLoader());
            System.out.println(clazz.getClassLoader().getParent());
            System.out.println(clazz.getClassLoader().getParent().getParent());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
