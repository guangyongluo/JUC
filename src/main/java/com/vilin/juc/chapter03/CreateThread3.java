package com.vilin.juc.chapter03;

public class CreateThread3 {

    private int i = 0;

    private byte[] bytes = new byte[1024];

    private static int counter = 0;

    //JVM will create a thread named "main"
    public static void main(String[] args) {
        //create a JVM stack
        int j = 0;

        int[] arr = new int[1024];

        int index = 0;

        try {
            add(index);
        }catch (Error error){
            System.out.println(counter);
            error.printStackTrace();
        }
    }

    private static void add(int i){
        counter++;
        add(i + 1);
    }
}
