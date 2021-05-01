package com.vilin.juc.utils.active;

import static java.lang.Thread.currentThread;

public class OrderServiceFactory {

    //将ActiveMessageQueue定义成static的目的是，保持其在整个JVM进程中是唯一的，并且ActiveDaemonThread
    //会在此启动

    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    //不允许外部通过new的方式构建
    private OrderServiceFactory() {
    }

    //返回OrderServiceProxy
    public static OrderService toActiveObject(OrderService orderService) {
        return new OrderServiceProxy(orderService, activeMessageQueue);
    }

    public static void main(String[] args) throws InterruptedException {
        //在创建OrderService时需要传递OrderService接口的具体实现
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());

        orderService.order("hello", 853883);

        //立即返回
        System.out.println("Return immediately");

        currentThread().join();
    }

}
