package com.vilin.concurrent.chapter06;


/**
 * ReadWriteLock design pattern
 * Reader-writer design pattern
 */
public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);

        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();

        new WriterWorker(sharedData, "qwertyuipasdfg").start();
        new WriterWorker(sharedData, "QWERTYUIPASDFG").start();
    }
}
