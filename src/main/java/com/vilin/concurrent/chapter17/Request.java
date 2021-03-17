package com.vilin.concurrent.chapter17;

public class Request {

    public final String name;

    public final int number;

    public Request(final String name, final int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString() {
        return "Request => No." + number + ", Name " + name;

    }
}
