package com.vilin.collections.blocking;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueExample {

    public static <T> LinkedTransferQueue<T> create(){
        return new LinkedTransferQueue<>();
    }
}
