package com.vilin.juc.utils.workthread;

public class Production extends InstructionBook{

    //产品编号
    private final int prodId;

    public Production(int prodId){
        this.prodId = prodId;
    }


    @Override
    protected void firstProcess() {
        System.out.println("execute the " + prodId + " first process");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + prodId + " second process");
    }
}
