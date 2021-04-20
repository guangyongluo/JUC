package com.vilin.collections.blocking;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public <T> PriorityBlockingQueue<T> create(int size){
        return new PriorityBlockingQueue<>(size);
    }

    public <T> PriorityBlockingQueue<T> create(int size, Comparator<T> comparator){
        return new PriorityBlockingQueue<>(size, comparator);
    }
}
