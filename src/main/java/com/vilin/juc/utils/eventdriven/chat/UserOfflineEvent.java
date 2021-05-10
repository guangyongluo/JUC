package com.vilin.juc.utils.eventdriven.chat;

public class UserOfflineEvent extends UserOnlineEvent{

    public UserOfflineEvent(User user) {
        super(user);
    }
}
