package com.vilin.concurrent.chapter01;

public class SingletonObject1 {

    /**
     * can't lazy load.
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1(){}

    public static SingletonObject1 getInstance(){
        return instance;
    }
}
