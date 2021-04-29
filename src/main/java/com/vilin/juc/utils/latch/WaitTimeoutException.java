package com.vilin.juc.utils.latch;

public class WaitTimeoutException extends Exception {
    public WaitTimeoutException(String message) {
        super(message);
    }
}
