package com.vilin.juc.utils.eventdriven.chat;

import com.vilin.juc.utils.eventdriven.eventdispatcher.Event;

public class UserOnlineEvent extends Event {

    private final User user;

    public UserOnlineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
