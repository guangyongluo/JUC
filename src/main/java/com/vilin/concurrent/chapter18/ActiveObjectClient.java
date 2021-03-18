package com.vilin.concurrent.chapter18;

public class ActiveObjectClient {

    //main
    public static void main(String[] args) {
//        System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread("Alice", activeObject, 'L').start();
        new MakerClientThread("Bobby", activeObject, 'W').start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
