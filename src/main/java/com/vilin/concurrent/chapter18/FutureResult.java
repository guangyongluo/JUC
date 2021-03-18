package com.vilin.concurrent.chapter18;

public class FutureResult implements Result{

    private Result result;

    private volatile boolean ready = false;

    public synchronized void setResult(Result result){
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }

    @Override
    public synchronized Object getResultValue() {
        while(!ready){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
