package com.vilin.collections.blocking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExampleTest {

    private LinkedBlockingQueueExample example;

    @Before
    public void setUp(){
        this.example = new LinkedBlockingQueueExample();
    }

    @After
    public void tearDown(){
        this.example = null;
    }

    @Test (expected = IllegalStateException.class)
    public void testInsertData() throws InterruptedException {
        LinkedBlockingQueue<String> queue = example.create(2);
        System.out.println("Hello, world");
//        Assert.assertEquals(queue.add("Data-1"), true);
//        Assert.assertEquals(queue.add("Data-2"), true);
//        Assert.assertEquals(queue.add("Data-3"), false);

        queue.clear();
        Assert.assertEquals(queue.offer("Data-1"), true);
        Assert.assertEquals(queue.offer("Data-2"), true);
        Assert.assertEquals(queue.offer("Data-3"), false);
        System.out.println(queue.size());


        queue.clear();
        queue.put("Data-1");
        System.out.println(queue.size());
        queue.put("Data-2");
        System.out.println(queue.size());
        queue.put("Data-3");
    }

    @Test
    public void testRemoveData() throws InterruptedException {
        LinkedBlockingQueue<String> queue = example.create(2);
        Assert.assertEquals(queue.offer("Data-1"), true);
        Assert.assertEquals(queue.offer("Data-2"), true);
        Assert.assertEquals(queue.offer("Data-3"), false);

        Assert.assertEquals(queue.element(), "Data-1");
        Assert.assertEquals(queue.element(), "Data-1");

        Assert.assertEquals(queue.peek(), "Data-1");
        Assert.assertEquals(queue.peek(), "Data-1");

        Assert.assertEquals(queue.remove(), "Data-1");
        Assert.assertEquals(queue.poll(), "Data-2");

        Assert.assertEquals(queue.size(), 0);
        Assert.assertEquals(queue.remainingCapacity(), 2);

        Assert.assertEquals(queue.take(), "XXXXX");
        Assert.fail("can not process here");
    }
}
