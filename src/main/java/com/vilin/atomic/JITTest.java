package com.vilin.atomic;

public class JITTest {

    private static boolean init = false;

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                while(!init){
                    System.out.println(".");
                    /**
                     * while(true){}
                     */
                }
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(){
            @Override
            public void run() {
                init = true;
                System.out.println("set init to true");
            }
        }.start();
    }
}
