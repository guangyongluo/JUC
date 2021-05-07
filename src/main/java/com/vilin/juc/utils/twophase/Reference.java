package com.vilin.juc.utils.twophase;

public class Reference {

    //1M
    private final byte[] data = new byte[2 << 19];

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("The reference will be GC.");
    }
}
