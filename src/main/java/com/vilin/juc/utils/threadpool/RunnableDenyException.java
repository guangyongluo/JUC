package com.vilin.juc.utils.threadpool;

public class RunnableDenyException extends Exception{

    public RunnableDenyException(String message){
        super(message);
    }
}
