package com.vilin.concurrent.chapter12;

public class BalkingTest {

    public static void main(String[] args) {
        new DocumentEditThread("text.txt").start();
    }
}
