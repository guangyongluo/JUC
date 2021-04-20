package com.vilin.collections.blocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * An unbounded based on linked nodes
 *
 * producer: when the capacity is full, the producer will blocked.
 * else just only insert the new element into the queue, but the data is consume or not?
 */
public class LinkedTransferQueueExampleTest {

    @Test
    public void testTryTransfer(){
        LinkedTransferQueue<Object> queue = LinkedTransferQueueExample.create();
        boolean result = queue.tryTransfer("Transfer");
        Assert.assertEquals(result, false);
        Assert.assertEquals(queue.size(), 0);
    }

    @Test
    public void testTransfer() throws InterruptedException {
        LinkedTransferQueue<Object> queue = LinkedTransferQueueExample.create();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> {
            try {
                Assert.assertEquals(queue.take(), "Transfer");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);
        service.shutdown();

        long currentTime = System.currentTimeMillis();

        queue.transfer("Transfer");
        Assert.assertEquals(queue.size(), 0);
        Assert.assertEquals((System.currentTimeMillis() - currentTime) >= 1000, true);
    }

    @Test
    public void testTransfer2() throws InterruptedException {
        LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
        Assert.assertEquals(queue.getWaitingConsumerCount(), 0);
        Assert.assertEquals(queue.hasWaitingConsumer(), false);

        List<Callable<String>> collect = IntStream.range(0, 5).boxed().map(i -> (Callable<String>) () -> {
            try {
                return queue.take();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        ExecutorService executorService = Executors.newCachedThreadPool();
        collect.stream().forEach(executorService::submit);

        TimeUnit.MILLISECONDS.sleep(5);

        Assert.assertEquals(queue.getWaitingConsumerCount(), 5);
        Assert.assertEquals(queue.hasWaitingConsumer(), true);
        System.out.println("======================================");

        IntStream.range(0, 5).boxed().map(String::valueOf).forEach(queue::add);

        TimeUnit.MILLISECONDS.sleep(10);
        Assert.assertEquals(queue.getWaitingConsumerCount(), 0);
        Assert.assertEquals(queue.hasWaitingConsumer(), false);
    }

    @Test
    public void testAdd() throws InterruptedException {
        LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
        Assert.assertEquals(queue.add("Hello"), true);
        Assert.assertEquals(queue.size(), 1);
    }

}