package com.vilin.juc.utils.eventdriven.eventdispatcher;

public interface Message {

    //返回Message的类型
    Class<? extends Message> getType();
}
