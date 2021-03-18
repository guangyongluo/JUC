package com.vilin.concurrent.chapter18;

public class MakerClientThread extends Thread{

    private final ActiveObject activeObject;

    private final char aChar;

    public MakerClientThread(String name, ActiveObject activeObject, char aChar) {
        super(name);
        this.activeObject = activeObject;
        this.aChar = aChar;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++){
            Result result = activeObject.makeString(i + 1, aChar);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String value = (String)result.getResultValue();
            System.out.println(Thread.currentThread().getName() + ": value = " + value);
        }
    }
}
