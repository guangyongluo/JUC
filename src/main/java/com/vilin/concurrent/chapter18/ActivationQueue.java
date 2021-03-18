package com.vilin.concurrent.chapter18;

import java.util.LinkedList;

public class ActivationQueue {

    private final static int MAX_ACTIVATION_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> activationQueue;

    public ActivationQueue() {
        this.activationQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest methodRequest){
        while(activationQueue.size() >= MAX_ACTIVATION_QUEUE_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        activationQueue.addLast(methodRequest);
        this.notifyAll();
    }

    public synchronized MethodRequest get(){
        while(activationQueue.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MethodRequest methodRequest = activationQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
