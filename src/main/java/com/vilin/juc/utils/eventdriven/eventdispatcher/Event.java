package com.vilin.juc.utils.eventdriven.eventdispatcher;

public class Event implements  Message{

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
