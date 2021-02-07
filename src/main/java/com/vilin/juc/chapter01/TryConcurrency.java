package com.vilin.juc.chapter01;

public class TryConcurrency {
    public static void main(String[] args) {

        new Thread("READ-Thread"){
            public void run(){
                readFromDataBase();
            }
        }.start();

        new Thread("WRITE-Thread"){
            public void run(){
                writeDataToFile();
            }
        }.start();

//        Thread t1 = new Thread("Customer Thread "){
//
//            public void run(){
//                for(int i = 0; i < 100; i++){
//                    println("Task i =>" + i);
//                }
//            }
//        };

//        t1.start();
//        for(int i = 0; i < 100; i++){
//            println("Task i =>" + i);
//        }
//        for(int j = 0; j < 100; j++){
//            println("Task j=>" + j);
//        }
//        readFromDataBase();
//        writeDataToFile();

//        try {
//            Thread.sleep(1000*300L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void readFromDataBase(){
        // read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000*10L);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }

    private static void writeDataToFile(){
        // read data from database and handle it.
        try {
            println("Begin write data to file.");
            Thread.sleep(1000*10L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("The data handle finish and successfully.");
    }

    private static void println(String message){
        System.out.println(message);
    }
}
