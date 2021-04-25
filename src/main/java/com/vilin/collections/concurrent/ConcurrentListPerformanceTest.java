package com.vilin.collections.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentListPerformanceTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 10; i <= 100; i += 10) {
            pressureTest(new ConcurrentLinkedQueue<>(), i);
            pressureTest(new CopyOnWriteArrayList<>(), i);
            pressureTest(Collections.synchronizedList(new ArrayList<>()), i);
        }
    }

    static class Entry {
        int threshold;
        long ms;

        public Entry(int threshold, long ms) {
            this.threshold = threshold;
            this.ms = ms;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "threshold=" + threshold +
                    ", ms=" + ms +
                    '}';
        }
    }

    private final static Map<String, List<Entry>> summary = new HashMap<>();

    private static void pressureTest(final Collection<String> list, int threshold) throws InterruptedException {
        System.out.println("Start pressure testing the map [" + list.getClass() + "] use the threshold [" + threshold + "]");
        long totalTime = 0L;
        final int MAX_THRESHOLD = 5000000;
        for (int i = 0; i < 5; i++) {
            final AtomicInteger counter = new AtomicInteger(0);
            list.clear();
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(() -> {
                    for (int k = 0; k < MAX_THRESHOLD && counter.getAndIncrement() < MAX_THRESHOLD; k++) {
                        Integer randomNumber = (int) Math.ceil(Math.random() * 6000000);
                        list.add(String.valueOf(randomNumber));
                    }
                });
            }

            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            System.out.println(MAX_THRESHOLD + " element add in " + period + " ms");
            totalTime += period;
        }

        List<Entry> entries = summary.get(list.getClass().getSimpleName());
        if (entries == null) {
            entries = new ArrayList<>();
            summary.put(list.getClass().getSimpleName(), entries);
        }
        entries.add(new Entry(threshold, totalTime / 5));
        System.out.println("For the map [" + list.getClass() + "] thr average time is " + (totalTime / 5) + " ms");
    }
}
