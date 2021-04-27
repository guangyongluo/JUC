package com.vilin.juc.utils.future;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FutureServiceImpl的主要作用在于当提交任务时创建一个新的线程来受理该任务，进而达到任务异步
 * 执行的结果
 * @param <IN>
 * @param <OUT>
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    //为执行的线程指定名字前缀
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName(){
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            //任务执行结束之后将null作为结果传给future
            future.finish(null);
        }, getNextName()).start();
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> future = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            //任务执行结束之后，将真实的结果是通过finish方法传递给future
            future.finish(result);
        }, getNextName()).start();

        return future;
    }
}
