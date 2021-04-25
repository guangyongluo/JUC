package com.vilin.collections.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.*;

public class ConcurrentSkipListMapExampleTest {

    @Test
    public void testCeiling(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        Assert.assertEquals(map.size(), 3);
        Assert.assertEquals((int)map.ceilingKey(2), 5);
        Assert.assertEquals(map.ceilingEntry(2).getValue(), "Java");
        Assert.assertEquals(map.ceilingEntry(5).getValue(), "Java");
    }

    @Test
    public void testFloor(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        Assert.assertEquals(map.size(), 3);
        Assert.assertEquals((int)map.floorKey(2), 1);
        Assert.assertEquals(map.floorEntry(2).getValue(), "Scala");
        Assert.assertEquals(map.floorEntry(1).getValue(), "Scala");
    }

    @Test
    public void testFirst(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        Assert.assertEquals(map.size(), 3);
        Assert.assertEquals((int)map.firstKey(), 1);
        Assert.assertEquals(map.firstEntry().getValue(), "Scala");
    }


    @Test
    public void testLast(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        Assert.assertEquals(map.size(), 3);
        Assert.assertEquals((int)map.lastKey(), 10);
        Assert.assertEquals(map.lastEntry().getValue(), "Clojure");
    }

    @Test
    public void testMerge(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        String result = map.merge(1, "C++", (ov, v) -> {
            Assert.assertEquals(ov, "Scala");
            Assert.assertEquals(v, "C++");
            return ov + v;
        });

        Assert.assertEquals(result, "ScalaC++");
        Assert.assertEquals(map.get(1), "ScalaC++");
    }

    @Test
    public void testTailMap(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        ConcurrentNavigableMap<Integer, String> navigableMap = map.tailMap(1);

        navigableMap.forEach((k, v) -> {
            System.out.println("key : " + k + " value : " + v);
        });
    }

    @Test
    public void testCompute(){
        ConcurrentSkipListMap<Integer, String> map = ConcurrentSkipListMapExample.create();

        map.put(1, "Scala");
        map.put(5, "Java");
        map.put(10, "Clojure");

        String result = map.compute(1, (k, v) -> {
            assertEquals(k, (Integer) 1);
            assertEquals(v, "Scala");
            return "Hello";
        });

        Assert.assertEquals(result, "Hello");
        Assert.assertEquals(map.get(1), "Hello");
    }

}