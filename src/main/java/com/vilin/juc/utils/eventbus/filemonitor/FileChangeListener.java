package com.vilin.juc.utils.eventbus.filemonitor;

import com.vilin.juc.utils.eventbus.Subscribe;

public class FileChangeListener {

    @Subscribe
    public void onChange(FileChangeEvent event){
        System.out.printf("%s-%s\n", event.getPath(), event.getKind());
    }
}
