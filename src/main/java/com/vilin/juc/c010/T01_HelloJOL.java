package com.vilin.juc.c010;

import org.openjdk.jol.info.ClassLayout;

public class T01_HelloJOL {

    public static void main(String[] args) {
        Object object = new Object();

        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object){
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
