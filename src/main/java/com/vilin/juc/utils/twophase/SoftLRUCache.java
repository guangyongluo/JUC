package com.vilin.juc.utils.twophase;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SoftLRUCache<K, V> {

    //用于记录key值的顺序
    private final LinkedList<K> keyList = new LinkedList<K>();

    //value采用SoftReference进行修饰
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    //cache的最大容量
    private final int capacity;

    //cacheLoader接口提供了一种加载数据的方式
    private final CacheLoader<K, V> cacheLoader;

    public SoftLRUCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value) {
        //当元素数量超过容量时，将最老的数据清除
        if (keyList.size() >= capacity) {
            K eldestKey = keyList.removeFirst(); //eldest data
            cache.remove(eldestKey);
        }
        //如果数据已经存在，则从key的队列中删除
        if (keyList.contains(key)) {
            keyList.remove(key);
        }

        //将key存放至队尾
        keyList.addLast(key);
        //保存SoftReference
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V value;
        //先将key从key list中删除
        boolean success = keyList.remove(key);
        if (!success) { //如果删除失败则表明该数据不存在
            //通过cacheloader对数据进行加载
            value = cacheLoader.load(key);
            //调用put方法cache数据
            this.put(key, value);
        }else{
            //如果删除成功，则从cache中返回数据，并且将key再次放到队尾
            value = cache.get(key).get();
            keyList.addLast(key);
        }

        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }
}
