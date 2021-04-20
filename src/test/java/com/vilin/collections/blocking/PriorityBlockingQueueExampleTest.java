package com.vilin.collections.blocking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueExampleTest {

    private PriorityBlockingQueueExample example;

    @Before
    public void setUp() {
        example = new PriorityBlockingQueueExample();
    }

    @After
    public void tearDown() {
        example = null;
    }

    @Test
    public void testAddNewElement() {
        PriorityBlockingQueue<String> queue = example.create(5);

        Assert.assertTrue(queue.add("Hello1"));
        Assert.assertTrue(queue.add("Hello2"));
        Assert.assertTrue(queue.add("Hello3"));
        Assert.assertTrue(queue.add("Hello4"));
        Assert.assertTrue(queue.add("Hello5"));
        Assert.assertTrue(queue.add("Hello6"));

        Assert.assertEquals(queue.size(), 6);
    }

    @Test
    public void testGetElement() throws InterruptedException {
        PriorityBlockingQueue<String> queue = example.create(3);

        Assert.assertTrue(queue.add("Hello5"));
        Assert.assertTrue(queue.add("Hello2"));
        Assert.assertTrue(queue.add("Hello3"));

        Assert.assertEquals(queue.element(), "Hello2");
        Assert.assertEquals(queue.size(), 3);
        Assert.assertEquals(queue.element(), "Hello2");
        Assert.assertEquals(queue.size(), 3);


        Assert.assertEquals(queue.peek(), "Hello2");
        Assert.assertEquals(queue.size(), 3);
        Assert.assertEquals(queue.peek(), "Hello2");
        Assert.assertEquals(queue.size(), 3);

        Assert.assertEquals(queue.poll(), "Hello2");
        Assert.assertEquals(queue.size(), 2);
        Assert.assertEquals(queue.poll(), "Hello3");
        Assert.assertEquals(queue.size(), 1);


        Assert.assertEquals(queue.take(), "Hello5");
        Assert.assertEquals(queue.size(), 0);

        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(() -> {
            queue.add("New_data");
        }, 1, TimeUnit.SECONDS);
        scheduledExecutor.shutdown();
        Assert.assertEquals(queue.take(), "New_data");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullElement() {
        PriorityBlockingQueue<String> queue = example.create(3);
        queue.add(null);
    }

    @Test(expected = ClassCastException.class)
    public void testAddUserWithNoComparableWithNoComparator() {
        PriorityBlockingQueue<UserWithNoComparable> queue = example.create(3);
        queue.add(new UserWithNoComparable());
        Assert.fail("should not to process here");
    }

    @Test
    public void testAddUserWithNoComparableWithComparator() {
        PriorityBlockingQueue<UserWithNoComparable> queue = example.create(3, (o1, o2) -> {
            return o1.hashCode() - o2.hashCode();
        });
        queue.add(new UserWithNoComparable());
    }

    static class UserWithNoComparable {

    }

}
