package com.vilin.classloader.chapter01;

public class Singleton {

    private static Singleton instance = new Singleton();

    public static int x = 0;

    public static int y;

    /**
     * 连接过程中的第二阶段准备阶段：准备阶段为类的静态变量分配内存，并将其初始化为默认值
     * int x = 0;
     * int y = 0;
     * instance = null;
     */

    /**
     * 第二阶段结束，初始化正式开始，按照声明顺序正确初始化静态变量
     */

    private Singleton(){
        x++;
        y++;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
