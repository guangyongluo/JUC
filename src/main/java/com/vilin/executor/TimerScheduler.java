package com.vilin.executor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerScheduler {

    /**
     * schedule solution
     * Timer / TimerTask
     * SchedulerExecutorService
     * crontab
     * cron4j
     * quartz
     *
     * Timer: Question
     * when the timertask process more than 1 seconds what happen?
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("======" + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.schedule(task, 1000, 1000);
    }
}
