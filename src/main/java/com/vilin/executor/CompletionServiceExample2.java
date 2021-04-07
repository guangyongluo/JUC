package com.vilin.executor;

import java.util.concurrent.*;

public class CompletionServiceExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        List<Callable<Integer>> callableList = Arrays.asList(
//                () -> {
//                    sleep(10);
//                    System.out.println("The 10 finished");
//                    return 10;
//                },
//                () -> {
//                    sleep(20);
//                    System.out.println("The 20 finished");
//                    return 20;
//                }
//        );
//
//        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
//        List<Future> Futures = new ArrayList<Future>();
//        callableList.stream().forEach(callable -> Futures.add(completionService.submit(callable)));

//        Future<Integer> future = null;
//        while((future = completionService.take()) != null){
//            System.out.println(future.get());
//        }

//        Future<Integer> future1 = completionService.poll();

//        Future future = completionService.poll(11, TimeUnit.SECONDS);
//        System.out.println(future);

        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(executorService);
        final Event event = new Event(1);
        completionService.submit(new MyTask(event), event);
        Future<Event> future = completionService.take();
        System.out.println(future.get().getResult());
    }

    private static class MyTask implements Runnable{

        private final Event event;

        private MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(10);
            event.setResult("I am successfully!");
        }
    }

    private static class Event{
        final private int eventId;
        private String result;

        private Event(int eventId) {
            this.eventId = eventId;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    private static void sleep(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
