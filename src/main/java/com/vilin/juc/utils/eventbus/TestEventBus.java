package com.vilin.juc.utils.eventbus;

public class TestEventBus {
    public static void main(String[] args) {
        Bus bus = new EventBus("TestBus");
        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());
        bus.post("Hello");
        System.out.println("----------------------");
        bus.post("Hello", "test");
    }
}
