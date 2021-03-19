package com.vilin.classloader.chapter04;

import com.vilin.classloader.chapter03.MyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        DecryptClassLoader classLoader = new DecryptClassLoader();
        Class<?> aClass = classLoader.loadClass("com.vilin.classloader.chapter03.MyObject");
        System.out.println(aClass);

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello", new Class<?>[]{});
        Object result = method.invoke(obj, new Object[]{});
        System.out.println(result);
    }
}
