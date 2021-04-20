package com.vilin.collections.blocking;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

    public static <T>SynchronousQueue<T> create(){
        return new SynchronousQueue<>();
    }

}
