package com.vilin.concurrent.chapter16;

import java.util.concurrent.TimeUnit;

public class AppServerClient {

    public static void main(String[] args) {
        AppServer server = new AppServer(33333);
        server.start();

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.shutdown();
    }
}
