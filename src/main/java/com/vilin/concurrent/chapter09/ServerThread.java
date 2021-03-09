package com.vilin.concurrent.chapter09;

import java.util.Random;

public class ServerThread extends Thread{

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = true;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    public void close(){
        this.flag = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (flag){
            Request request = queue.getRequest();
            if(null == request){
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server ->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
