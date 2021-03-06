package com.vilin.concurrent.chapter07;

public class ImmutablePerformance {

    public static void main(String[] args) {
        long startTimeStamp = System.currentTimeMillis();
        SyncObj syncObj = new SyncObj();
        syncObj.setName("Alex");

//        ImmutableObj immutableObj = new ImmutableObj("Alex");
//
//        for(long l = 0L; l < 1000000; l++){
//            System.out.println(immutableObj.toString());
//        }

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(syncObj.toString());
                }
            }
        };

        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(syncObj.toString());
                }
            }
        };

        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTimeStamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimeStamp - startTimeStamp));
    }

}

final class ImmutableObj {

    private final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

class SyncObj {

    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "SyncObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
