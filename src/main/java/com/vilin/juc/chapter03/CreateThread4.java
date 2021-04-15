package com.vilin.juc.chapter03;

public class CreateThread4 {

    private static int counter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(null,new Runnable() {
            @Override
            public void run() {
                try{
                    add(0);
                }catch (Error error){
                    System.out.println(counter);
                }
            }

            private void add(int i){
                counter++;
                add(i + 1);
            }
        },"ActiveObjectClient",1<<24);

        t1.start();
    }
}
