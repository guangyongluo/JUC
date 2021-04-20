package com.vilin.collections.blocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


public class SynchronousQueueExampleTest {

    @Test
    public void testAdd() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Assert.assertEquals(queue.take(), "SynchronousQueue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);
        Assert.assertEquals(queue.add("SynchronousQueue"), true);
    }

    @Test
    public void testOffer() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Assert.assertEquals(queue.take(), "SynchronousQueue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.MILLISECONDS.sleep(5);
        Assert.assertEquals(queue.offer("SynchronousQueue"), true);
    }

    @Test
    public void testPut() throws InterruptedException {
        SynchronousQueue<String> queue = SynchronousQueueExample.create();

        queue.put("SynchronousQueue");
        Assert.fail("should not process to here.");
    }

}