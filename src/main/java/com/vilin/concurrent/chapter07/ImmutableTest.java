package com.vilin.concurrent.chapter07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {

    private final int age;

    private final String name;

    private final List<String> list;

    public ImmutableTest(int age, String name){
        this.age = age;
        this.name = name;
        this.list = new ArrayList<String>();
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }
}
