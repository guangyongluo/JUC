package com.vilin.juc.utils.eventdriven.eventdispatcher;

import java.util.HashMap;
import java.util.Map;

//EventDispatcher不是一个线程安全地类
public class EventDispatcher implements DynamicRouter<Message>{

    //用于保存Channel和Message之间的关系
    private final Map<Class<? extends Message>, Channel> routerTable;

    //初始化RouterTable，但是在该实现中，我们使用HashMap作为路由表
    public EventDispatcher() {
        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if(routerTable.containsKey(message.getType())){
            //直接获取对应的Channel处理Message
            routerTable.get(message.getType()).dispatch(message);
        }else {
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() + "] type");
        }
    }
}
