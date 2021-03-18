package com.vilin.concurrent.chapter18;

public class ActiveObjectFactory {

    private ActiveObjectFactory(){

    }

    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(activationQueue);
        ActiveObjectProxy activeObjectProxy = new ActiveObjectProxy(schedulerThread, servant);
        schedulerThread.start();
        return activeObjectProxy;
    }


}
