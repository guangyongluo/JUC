package com.vilin.collections.blocking;

import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeExample {

    public static  <T> LinkedBlockingDeque<T> create(){
        return new LinkedBlockingDeque<>();
    }
}
