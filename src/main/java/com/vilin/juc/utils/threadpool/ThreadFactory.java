package com.vilin.juc.utils.threadpool;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
