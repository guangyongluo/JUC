package com.vilin.juc.utils.latch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ProgrammerTravel extends Thread {

    //门阀
    private final Latch latch;

    //程序员
    private final String programmer;

    //交通工具
    private final String transportation;

    //通过构造函数传入latch,programmer,transportation
    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation [" + transportation + "]");
        try {
            //程序员乘坐交通工具花费在路上的时间(使用随机数字模拟)
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportation);
        //完成任务时使计数器减一
        latch.countDown();
    }
}
