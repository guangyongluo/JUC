package com.vilin.concurrent.chapter05;

/**
 * SharedResource
 */
public class Gate {

    private int counter = 0;

    private String name = "Nobody";

    private String address = "NoWhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address){
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if(this.name.charAt(0) != this.address.charAt(0)){
            System.out.println("*****************BROKEN****************" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
