package com.vilin.juc.utils.active;

import com.vilin.juc.utils.future.FutureTask;

public class ActiveFuture<T> extends FutureTask<T> {
    @Override
    public void finish(T result) {
        super.finish(result);
    }
}
