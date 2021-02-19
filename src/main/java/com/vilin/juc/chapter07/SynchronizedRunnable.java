package com.vilin.juc.chapter07;

public class SynchronizedRunnable implements Runnable{

    private int index = 1;

    //readonly
    private final static int MAX = 500;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while (true){
            if(ticket())
                break;
        }
    }

    public synchronized boolean ticket(){
        //get field.
        if(index > MAX)
            return true;

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //index++ => index + 1;
        //1.get field index;
        //2.index = index + 1;
        //3.put field index;
        System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
        return false;
    }
}
