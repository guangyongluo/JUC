package com.vilin.concurrent.chapter12;

import java.util.concurrent.TimeUnit;

public class AutoSaveThread extends Thread{

    private final Document document;

    public AutoSaveThread(Document document){
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while(true){
            try {
                document.save();
                TimeUnit.SECONDS.sleep(1);
                System.out.println("auto save finished.");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
