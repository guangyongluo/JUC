package com.vilin.juc.utils.eventdriven.chat;

import com.vilin.juc.utils.eventdriven.eventdispatcher.AsyncChannel;
import com.vilin.juc.utils.eventdriven.eventdispatcher.Event;

//用户下线的Event，简单输出用户下线即可
public class UserOfflineEventChannel extends AsyncChannel {
    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("The User[" + event.getUser().getName() + "] is offline.");
    }
}
