package com.vilin.juc.utils.eventdriven;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class FooEventDrivenExample {

    //用于处理A类型的Event
    public static void handleEventA(Event e) {
        System.out.println(e.getData().toLowerCase());
    }

    //用于处理B类型的Event
    public static void handleEventB(Event e) {
        System.out.println(e.getData().toUpperCase());
    }

    public static void main(String[] args) {
        Queue<Event> events = new LinkedList<>();
        events.add(new Event("A", "Hello"));
        events.add(new Event("A", "I am Event A"));
        events.add(new Event("B", "I am Event B"));
        events.add(new Event("B", "World"));
        Event e;
        while (!events.isEmpty()) {
            //从消息队列中不断移除，根据不同的类型进行处理
            e = events.remove();
            switch (e.getType()) {
                case "A":
                    handleEventA(e);
                    break;
                case "B" :
                    handleEventB(e);
                    break;
            }
        }
    }
}
