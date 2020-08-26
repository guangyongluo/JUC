package com.vilin.juc.c008;

import java.lang.ref.WeakReference;

public class T02_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<M>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
    }
}
