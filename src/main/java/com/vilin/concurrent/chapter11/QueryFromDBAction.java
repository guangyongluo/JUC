package com.vilin.concurrent.chapter11;

import com.vilin.concurrent.chapter10.ThreadLocalSimulator;

public class QueryFromDBAction {

    public void execute(){
        try {
            Thread.sleep(1000L);
            String name = "Alex" + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
