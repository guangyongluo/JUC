package com.vilin.juc.utils.eventdriven.chat;

import com.vilin.juc.utils.eventdriven.eventdispatcher.AsyncChannel;
import com.vilin.juc.utils.eventdriven.eventdispatcher.Event;

//用户聊天的Event，直接在控制台输出即可
public class UserChatEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("The User[" + event.getUser().getName() + "] say: " + event.getMessage());
    }
}
