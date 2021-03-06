package com.vilin.concurrent.chapter07;

import java.util.stream.IntStream;

public class ImmutableClient {

    public static void main(String[] args) {
        Person person = new Person("Alex", "GuanSu");
        IntStream.range(0, 5).forEach(i -> new UsePersonThread(person).start());
    }
}
