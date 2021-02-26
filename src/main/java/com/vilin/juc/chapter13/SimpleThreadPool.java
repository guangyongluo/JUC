package com.vilin.juc.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool extends Thread {

    private int size;

    private final int queueSize;

    private final DiscardPolicy discardPolicy;

    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    private static volatile int seg = 0;

    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("POOL_Group");

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<Runnable>();

    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<WorkerTask>();

    private volatile boolean destroy = false;

    private int min;

    private int max;

    private int active;

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("discards this task");
    };

    public SimpleThreadPool() {
        this(3, 8, 15, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.max = max;
        this.active = active;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < min; i++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seg++));
        task.start();
        synchronized (THREAD_QUEUE) {
            THREAD_QUEUE.add(task);
        }
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String msg) {
            super(msg);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("this thread pool has already destroyed and not allow to submit task.");
        }

        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public void shutdown() {

        while (!TASK_QUEUE.isEmpty()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (THREAD_QUEUE) {
            int intValue = THREAD_QUEUE.size();
            while (intValue > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKED) {
                        task.interrupt();
                        task.close();
                        intValue--;
                    } else {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        System.out.println(GROUP.getName());

        this.destroy = true;
        System.out.println("the thread pool disposed.");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    @Override
    public void run() {
        while (!destroy) {
            System.out.printf("Pool => #Min:%d, #Active:%d, #Max:%d, #Current:%d, #QueueSize:%d \n",
                    this.min, this.active, this.max, THREAD_QUEUE.size(), TASK_QUEUE.size());

            try {
                Thread.sleep(5_000L);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented.");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }

                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return taskState;
        }

        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed.");
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }

                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();

        for (int i = 0; i < 100; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " started.");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " finished.");
            });
        }

//        IntStream.rangeClosed(0, 30).forEach(i -> {
//            threadPool.submit(() -> {
//                System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + " started.");
//                try {
//                    Thread.sleep(1_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread().getName() + " finished.");
//            });
//        });

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println("===============");
//
        threadPool.shutdown();
//        System.out.println("===============");
//        threadPool.destroy =true;

//        threadPool.submit(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
