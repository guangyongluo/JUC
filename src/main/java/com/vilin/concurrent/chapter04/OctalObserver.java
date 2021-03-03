package com.vilin.concurrent.chapter04;

public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}
