package com.vilin.juc.utils.eventdriven.eventdispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsyncChannel implements Channel<Event> {

    //在AsyncChannel中将使用ExecutorService多线程的方式提交给Message
    private final ExecutorService executorService;

    //默认构造函数，提供了CPU的核数*2的线程数量
    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    //用户自定义的ExecutorService
    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    //重写dispatch方法，并且用final修饰，避免子类重写
    @Override
    public void dispatch(Event message) {
        executorService.submit(() -> this.handle(message));
    }

    //提供抽象方法，供子类实现具体的Message处理
    protected abstract void handle(Event message);

    //提供关闭ExecutorService的方法
    void stop(){
        if(null != executorService && !executorService.isShutdown())
            executorService.shutdown();
    }
}
