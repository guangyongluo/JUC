package com.vilin.concurrent.chapter11;

import com.vilin.concurrent.chapter10.ThreadLocalSimulator;

public class QueryFromHttpAction {

    public void execute(){
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "35102834" + Thread.currentThread().getId();
    }
}
