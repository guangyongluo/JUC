package com.vilin.juc.utils.latch;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            //当limit>0时，当前线程进入阻塞状态
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if(limit < 0) {
                throw new IllegalStateException("all of task already arrived");
            }
            //使limit减一，并且通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnArrived() {
        //返回有多少线程还未完成任务
        return limit;
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {
        if(time <= 0) {
            throw new IllegalArgumentException("The Time is invalid");
        }

        long remainingNanos = unit.toNanos(time);
        //等待任务将在endNanos纳秒后超时
        final long endNanos = System.nanoTime() + remainingNanos;

        synchronized (this) {
            while(limit > 0) {
                //如果超时则抛出WaitTimeoutException异常
                if(TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0){
                    throw new WaitTimeoutException("The wait time over specify time.");
                }

                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
    }
}
