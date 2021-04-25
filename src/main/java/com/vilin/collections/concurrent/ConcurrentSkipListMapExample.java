package com.vilin.collections.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {

    public static <K, V> ConcurrentSkipListMap<K, V> create(){
        return new ConcurrentSkipListMap<>();
    }
}
