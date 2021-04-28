package com.vilin.juc.utils.balking;

public class BalkingTest {

    public static void main(String[] args) {
        new DocumentEditThread("/Users/luowei/Documents", "balking.txt").start();
    }
}
