package com.vilin.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class FailedAtomicIntegerFieldUpdaterTest {

    @Test(expected = RuntimeException.class)
    public void testPrivateFieldAccessError(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1);
    }


    @Test
    public void testTargetObjectIsNull(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        updater.compareAndSet(null, 0, 1);
    }


    @Test
    public void testFieldNameInvalid(){
        AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer.class, "i");
        TestMe2 me = new TestMe2();
        updater.compareAndSet(me, null, 1);
    }

    static class TestMe2{
        Integer i;
    }
}
