package com.vilin.juc.utils.workthread;

public abstract class InstructionBook {

    public final void create(){
        this.firstProcess();
        this.secondProcess();
    }

    protected abstract void firstProcess();
    protected abstract void secondProcess();
}
