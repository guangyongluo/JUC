package com.vilin.collections.blocking;

import org.junit.Assert;
import org.junit.Test;

import java.io.Closeable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LinkedBlockingDequeExampleTest {

    static class F {
        public void foo() {
            System.out.println("======foo======");
        }
    }

    @Test
    public void testLambda() {
        List<F> fList = Arrays.asList(new F(), new F());

        Iterable<Closeable> iterator = (Iterable<Closeable>) fList.stream().map(f -> (Closeable)f::foo).iterator();

        /**
         * 1. no arguments
         * 2. no return value
         */
    }

    @Test
    public void testAddFirst(){
        LinkedBlockingDeque deque = LinkedBlockingDequeExample.create();
        deque.addFirst("Java");
        deque.addFirst("Scala");
        Assert.assertEquals(deque.removeFirst(), "Scala");
        Assert.assertEquals(deque.removeFirst(), "Java");
    }

    @Test
    public void testAdd(){
        LinkedBlockingDeque deque = LinkedBlockingDequeExample.create();
        deque.add("Java");
        deque.add("Scala");
        Assert.assertEquals(deque.remove(), "Java");
        Assert.assertEquals(deque.remove(), "Scala");
    }

    @Test
    public void testTakeFirst() throws InterruptedException {
        LinkedBlockingDeque deque = LinkedBlockingDequeExample.create();

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> deque.add("Take"), 1, TimeUnit.SECONDS);

        long currentTime = System.currentTimeMillis();
        Assert.assertEquals(deque.takeFirst(), "Take");
        Assert.assertEquals((System.currentTimeMillis() - currentTime) >= 1000, true);
    }

}