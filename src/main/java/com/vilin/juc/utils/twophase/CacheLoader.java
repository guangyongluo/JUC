package com.vilin.juc.utils.twophase;

@FunctionalInterface
public interface CacheLoader<K, V> {

    //定义加载数据的方法
    V load(K k);

}
