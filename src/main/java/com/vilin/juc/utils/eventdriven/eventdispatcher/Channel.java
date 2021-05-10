package com.vilin.juc.utils.eventdriven.eventdispatcher;

public interface Channel<E extends Message> {

    //dispatch方法用于负责Message的调度
    void dispatch(E message);
}
