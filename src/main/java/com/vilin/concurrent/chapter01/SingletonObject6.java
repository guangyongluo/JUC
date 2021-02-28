package com.vilin.concurrent.chapter01;

public class SingletonObject6 {

    private SingletonObject6(){

    }

    //static inner class
    private static class InstanceHolder{
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }
}
