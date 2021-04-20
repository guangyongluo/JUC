package com.vilin.collections.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueExample {


    // FIFO(First In First Out)
    // Once created, the capacity cannot be changed.
    public <T> ArrayBlockingQueue<T> create(int size){
        return new ArrayBlockingQueue<>(size);
    }
}
