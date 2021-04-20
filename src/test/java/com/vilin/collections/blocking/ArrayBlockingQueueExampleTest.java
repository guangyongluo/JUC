package com.vilin.collections.blocking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayBlockingQueueExampleTest {

    private ArrayBlockingQueueExample example;

    @Before
    public void setUp() {
        example = new ArrayBlockingQueueExample();
    }

    @After
    public void tearDown() {
        example = null;
    }

    @Test
    public void testAddMethodNotExceedCapacity() {
        ArrayBlockingQueue<String> queue = example.create(5);
        assertTrue(queue.add("Hello1"));
        assertTrue(queue.add("Hello2"));
        assertTrue(queue.add("Hello3"));
        assertTrue(queue.add("Hello4"));
        assertTrue(queue.add("Hello5"));

        assertEquals(queue.size(), 5);
    }

    @Test(expected = IllegalStateException.class)
    public void testAddMethodExceedCapacity() {
        ArrayBlockingQueue<String> queue = example.create(5);
        assertTrue(queue.add("Hello1"));
        assertTrue(queue.add("Hello2"));
        assertTrue(queue.add("Hello3"));
        assertTrue(queue.add("Hello4"));
        assertTrue(queue.add("Hello5"));
        assertTrue(queue.add("Hello6"));
        fail("should not process to here.");
    }

    @Test
    public void testOfferMethodExceedCapacity() {
        ArrayBlockingQueue<String> queue = example.create(5);
        assertTrue(queue.offer("Hello1"));
        assertTrue(queue.offer("Hello2"));
        assertTrue(queue.offer("Hello3"));
        assertTrue(queue.offer("Hello4"));
        assertTrue(queue.offer("Hello5"));
        assertFalse(queue.offer("Hello6"));

        assertEquals(queue.size(), 5);
    }

    @Test
    public void testPutMethodNotExceedCapacity() throws InterruptedException {
        ArrayBlockingQueue<String> queue = example.create(5);
        queue.put("Hello1");
        queue.put("Hello2");
        queue.put("Hello3");
        queue.put("Hello4");
        queue.put("Hello5");

        assertEquals(queue.size(), 5);
    }

    @Test
    public void testPutMethodExceedCapacity() throws InterruptedException {
        ArrayBlockingQueue<String> queue = example.create(5);

        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            try {
                assertEquals(queue.take(), "Hello1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);

        queue.put("Hello1");
        queue.put("Hello2");
        queue.put("Hello3");
        queue.put("Hello4");
        queue.put("Hello5");
        queue.put("Hello6");

    }

    @Test
    public void testPoll() {
        ArrayBlockingQueue<String> queue = example.create(2);
        queue.add("Hello1");
        queue.add("Hello2");

        assertEquals(queue.poll(), "Hello1");
        assertEquals(queue.poll(), "Hello2");
        assertNull(queue.poll());
    }

    @Test
    public void testPeek() {
        ArrayBlockingQueue<String> queue = example.create(2);
        queue.add("Hello1");
        queue.add("Hello2");

        assertEquals(queue.peek(), "Hello1");
        assertEquals(queue.peek(), "Hello1");
        assertEquals(queue.peek(), "Hello1");
        assertEquals(queue.peek(), "Hello1");
        assertEquals(queue.peek(), "Hello1");

    }

    @Test(expected = NoSuchElementException.class)
    public void testElement() {
        ArrayBlockingQueue<String> queue = example.create(2);
        queue.add("Hello1");
        queue.add("Hello2");

        assertEquals(queue.element(), "Hello1");
        assertEquals(queue.element(), "Hello1");
        assertEquals(queue.element(), "Hello1");
        assertEquals(queue.element(), "Hello1");
        assertEquals(queue.element(), "Hello1");
        queue.clear();
        assertEquals(queue.element(), "Hello1");

    }

    @Test(expected = NoSuchElementException.class)
    public void testRemove() {
        ArrayBlockingQueue<String> queue = example.create(2);
        queue.add("Hello1");
        queue.add("Hello2");

        assertEquals(queue.remove(), "Hello1");
        assertEquals(queue.remove(), "Hello2");

        assertEquals(queue.remove(), "Hello1");

    }

    @Test
    public void testDrainTo() {
        ArrayBlockingQueue<String> queue = example.create(5);
        queue.add("Hello1");
        queue.add("Hello2");
        queue.add("Hello3");

        assertEquals(queue.size(), 3);
        assertEquals(queue.remainingCapacity(), 2);
        assertEquals(queue.remove(), "Hello1");
        assertEquals(queue.size(), 2);
        assertEquals(queue.remainingCapacity(), 3);

        List<String> list = new ArrayList<>();
        queue.drainTo(list);
        assertEquals(list.size(), 2);

        assertEquals(queue.size(), 0);
        assertEquals(queue.remainingCapacity(), 5);

    }
}