package com.vilin.juc.utils.balking;

import java.io.IOException;
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
            try{
                //每隔一秒自动保存一次文档
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
