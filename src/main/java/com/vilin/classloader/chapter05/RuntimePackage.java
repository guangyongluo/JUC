package com.vilin.classloader.chapter05;

public class RuntimePackage {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.vilin.classloader.chapter05.SimpleObject");
        //System.out.println(aClass.getClassLoader());

        SimpleObject simpleObject = (SimpleObject) aClass.newInstance();
    }
}
