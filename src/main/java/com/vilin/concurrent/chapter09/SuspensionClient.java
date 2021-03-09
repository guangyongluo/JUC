package com.vilin.concurrent.chapter09;

public class SuspensionClient {

    public static void main(String[] args) {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
//        try {
//            serverThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        serverThread.close();

    }

}
