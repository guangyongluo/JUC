package com.vilin.concurrent.chapter08;

public interface Future<T> {

    T get() throws InterruptedException;


}
