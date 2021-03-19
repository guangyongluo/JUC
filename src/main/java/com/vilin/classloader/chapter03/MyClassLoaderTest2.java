package com.vilin.classloader.chapter03;

/**
 * 1.类加载器的委托是优先交给父亲加载器先去尝试加载
 * 2.父加载器和子加载器其实是一种包装关系，或者包含关系
 */
public class MyClassLoaderTest2 {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        MyClassLoader classLoader2 = new MyClassLoader(classLoader1, "MyClassLoader-2");

        classLoader2.setDir("/Users/luowei/Documents/classLoader2/");
        Class<?> aClass = classLoader2.loadClass("com.vilin.classloader.chapter03.MyObject");
        System.out.println(aClass);
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
    }
}
