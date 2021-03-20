package com.vilin.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {

        AtomicReference<Simple> atomicReference = new AtomicReference<Simple>(new Simple("Alex", 26));

        System.out.println(atomicReference.get());

        boolean result = atomicReference.compareAndSet(new Simple("lwei", 35), new Simple("Julia", 39));
        System.out.println(result);


    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
