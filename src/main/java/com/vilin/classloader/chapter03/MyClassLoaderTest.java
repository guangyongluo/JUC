package com.vilin.classloader.chapter03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        Class<?> clazz = classLoader.loadClass("com.vilin.classloader.chapter03.MyObject");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());

        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("hello", new Class<?>[]{});
        Object result = method.invoke(obj, new Object[]{});
        System.out.println(result);
    }
}
