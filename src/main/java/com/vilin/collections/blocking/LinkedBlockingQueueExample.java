package com.vilin.collections.blocking;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {


    public <T> LinkedBlockingQueue<T> create(){
        return new LinkedBlockingQueue<>();
    }

    public <T> LinkedBlockingQueue<T> create(int size){
        return new LinkedBlockingQueue<>(size);
    }
}
