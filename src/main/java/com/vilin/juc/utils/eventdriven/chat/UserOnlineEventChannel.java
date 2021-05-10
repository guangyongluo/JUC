package com.vilin.juc.utils.eventdriven.chat;

import com.vilin.juc.utils.eventdriven.eventdispatcher.AsyncChannel;
import com.vilin.juc.utils.eventdriven.eventdispatcher.Event;

//用户上线的Event，简单输出用户上线即可
public class UserOnlineEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserOnlineEvent event = (UserOnlineEvent) message;
        System.out.println("The User[" + event.getUser().getName() + "] is online.");
    }
}
