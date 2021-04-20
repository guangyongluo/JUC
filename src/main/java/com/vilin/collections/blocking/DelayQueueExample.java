package com.vilin.collections.blocking;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 1. The delay queue will ordered by expired time? yes
 * 2. when poll the empty delay queue will return null? use take?
 * 3. When less the expire time will return immediately? (take will waiting, peek or poll will return null)
 * 4. Even though unexpired elements cannot be removed using take or poll.(take will waiting, but poll not)
 * 5. This queue does not permit null elements.
 * 6. Use iterator can return immediately
 *
 * NOTICE: The DelayQueue elements must implement the delayed.
 *         The DelayQueue is a unbounded queue.
 */
public class DelayQueueExample {


    public static <T extends Delayed> DelayQueue<T> create(){
        return new DelayQueue<>();
    }
}
