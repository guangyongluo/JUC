package com.vilin.juc.utils.twophase;

import java.util.concurrent.TimeUnit;

public class LRUCacheTest {

    public static void main(String[] args) {
        SoftLRUCache<String, Reference> cache = new SoftLRUCache<>(100, key -> new Reference());
//        cache.get("Alex");
//        cache.get("Jack");
//        cache.get("Gavin");
//        cache.get("Dillon");
//        cache.get("Leo");
//
//        cache.get("Jenny");
//        System.out.println(cache.toString());

        for(int i = 0; i < Integer.MAX_VALUE; i++){
            cache.get(Integer.toString(i));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The " + i + " reference stored at cache.");
        }
    }
}
