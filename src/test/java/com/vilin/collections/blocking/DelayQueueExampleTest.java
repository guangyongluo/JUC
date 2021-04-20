package com.vilin.collections.blocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class DelayQueueExampleTest {

    /**
     * 1.Add method must add the delayed element.
     * 2.peek method will return null/element(be not remove) be quickly.
     * @throws InterruptedException
     */
    @Test
    public void testAdd1() throws InterruptedException {

        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();

        DelayElement<String> delay01 = DelayElement.of("Delayed01", 1000);
        delayQueue.add(delay01);

        Assert.assertEquals(delayQueue.size(), 1);
        long startTime = System.currentTimeMillis();
        Assert.assertEquals(delayQueue.take(), delay01);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void testAdd2() throws InterruptedException {

        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();

        delayQueue.add(DelayElement.of("Delayed01", 1000));
        delayQueue.add(DelayElement.of("Delayed02", 800));
        delayQueue.add(DelayElement.of("Delayed03", 11000));
        delayQueue.add(DelayElement.of("Delayed04", 18000));
        delayQueue.add(DelayElement.of("Delayed05", 21000));

        Assert.assertEquals(delayQueue.size(), 5);
        long startTime = System.currentTimeMillis();
        Iterator<DelayElement<String>> it = delayQueue.iterator();
        while(it.hasNext()){
            Assert.assertNotNull(it.next());
        }
        System.out.println(System.currentTimeMillis() - startTime);
        Assert.assertTrue((System.currentTimeMillis() - startTime) < 2);

    }

    @Test
    public void testAdd3() throws InterruptedException {

        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();

        delayQueue.add(DelayElement.of("Delayed01", 1000));
        delayQueue.add(DelayElement.of("Delayed02", 800));
        delayQueue.add(DelayElement.of("Delayed03", 11000));
        delayQueue.add(DelayElement.of("Delayed04", 18000));
        delayQueue.add(DelayElement.of("Delayed05", 21000));

        Assert.assertEquals(delayQueue.size(), 5);

        Assert.assertEquals(delayQueue.remove().getData(), "Delayed02");

    }

    @Test (expected = NoSuchElementException.class)
    public void testAdd4() throws InterruptedException {

        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();

        delayQueue.add(DelayElement.of("Delayed01", 1000));
        delayQueue.add(DelayElement.of("Delayed02", 800));
        delayQueue.add(DelayElement.of("Delayed03", 11000));
        delayQueue.add(DelayElement.of("Delayed04", 18000));
        delayQueue.add(DelayElement.of("Delayed05", 21000));

        Assert.assertEquals(delayQueue.size(), 5);

        Assert.assertEquals(delayQueue.remove().getData(), "Delayed02");

    }

    @Test
    public void testAdd5() throws InterruptedException {

        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();

        delayQueue.add(DelayElement.of("Delayed01", 1000));
        delayQueue.add(DelayElement.of("Delayed02", 800));
        delayQueue.add(DelayElement.of("Delayed03", 11000));
        delayQueue.add(DelayElement.of("Delayed04", 18000));
        delayQueue.add(DelayElement.of("Delayed05", 21000));

        Assert.assertEquals(delayQueue.size(), 5);

        Assert.assertEquals(delayQueue.take().getData(), "Delayed02");
        Assert.assertEquals(delayQueue.take().getData(), "Delayed01");
        Assert.assertEquals(delayQueue.take().getData(), "Delayed03");
        Assert.assertEquals(delayQueue.take().getData(), "Delayed04");
        Assert.assertEquals(delayQueue.take().getData(), "Delayed05");
    }

    @Test
    public void testPoll() {
        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();
        Assert.assertEquals(delayQueue.poll(), null);
    }

    @Test
    public void testTake() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = DelayQueueExample.create();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> delayQueue.add(DelayElement.of("Test", 1000)), 1, TimeUnit.SECONDS);
        executorService.shutdown();
        long startTime = System.currentTimeMillis();
        Assert.assertEquals(delayQueue.take().getData(), "Test");
        Assert.assertEquals((System.currentTimeMillis() - startTime) >= 1000, true);
    }

    static class DelayElement<E> implements Delayed {

        private final E e;

        private final long expiredTime;

        public DelayElement(E e, long delay) {
            this.e = e;
            this.expiredTime = System.currentTimeMillis() + delay;
        }

        static <T> DelayElement<T> of(T t, long delay){
            return new DelayElement<>(t, delay);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expiredTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayedObject) {
            DelayElement that = (DelayElement) delayedObject;
            if (this.expiredTime < that.getExpiredTime()) {
                return -1;
            } else if (this.expiredTime > that.getExpiredTime()) {
                return 1;
            } else {
                return 0;
            }
        }

        public E getData() {
            return this.e;
        }

        public long getExpiredTime() {
            return this.expiredTime;
        }
    }

}