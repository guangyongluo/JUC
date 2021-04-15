package com.vilin.concurrent.chapter08;

/**
 * Future -> 代表未来的凭据
 * FutureTask -> 调用逻辑进行了隔离
 * FutureService -> 桥接Future和FutureTask
 */
public class SyncInvoker {

    public static void main(String[] args) {
//        String result = get();
//        System.out.println(result);

        FutureService futureService = new FutureService();
        Future future = futureService.submit(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        System.out.println("====================");
        System.out.println("do other thing.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====================");
//        try {
//            System.out.println(Future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static String get(){
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "FINISH";
    }
}
