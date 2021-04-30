package com.vilin.juc.utils.perthread;

import com.vilin.juc.utils.threadpool.RunnableDenyException;

import java.io.IOException;

public class ChatTest {

    public static void main(String[] args) throws IOException, RunnableDenyException {
        new ChatServer().startServer();
    }
}
