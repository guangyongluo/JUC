package com.vilin.classloader.chapter01;

public class LoaderClass {

    public static void main(String[] args) {
//        MyObject myObject1 = new MyObject();
//        MyObject myObject2 = new MyObject();
//        MyObject myObject3 = new MyObject();
//        MyObject myObject4 = new MyObject();
//
//        System.out.println(myObject1.getClass() == myObject2.getClass());
//        System.out.println(myObject1.getClass() == myObject3.getClass());
//        System.out.println(myObject1.getClass() == myObject4.getClass());

        Sub sub = new Sub();
    }
}

class MyObject{

    public static int x = 10;

    static {
        System.out.println(x);
        x = 10 + 1;

        y = 200;
//        System.out.println(y);
    }

    private static int y = 20;
}

class parent{
    static {
        System.out.println("parent");
    }
}

class Sub extends parent{
    static {
        System.out.println("child");
    }
}
