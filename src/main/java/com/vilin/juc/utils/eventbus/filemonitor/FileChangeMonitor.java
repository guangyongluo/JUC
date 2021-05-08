package com.vilin.juc.utils.eventbus.filemonitor;

import com.vilin.juc.utils.eventbus.AsyncEventBus;
import com.vilin.juc.utils.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FileChangeMonitor {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        final EventBus eventBus = new AsyncEventBus(executor);
        //注册
        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "//tmp//monitor");
        monitor.startMonitor();
    }
}
