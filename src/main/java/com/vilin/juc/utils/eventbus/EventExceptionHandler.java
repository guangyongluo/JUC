package com.vilin.juc.utils.eventbus;

public interface EventExceptionHandler {
    void handle(Throwable cause, EventContext context);
}
