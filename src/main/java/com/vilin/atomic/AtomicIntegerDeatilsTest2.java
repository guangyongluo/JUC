package com.vilin.atomic;

public class AtomicIntegerDeatilsTest2 {

    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {

        for(int i = 0; i < 100; i++){
            new Thread(){
                @Override
                public void run() {
                    doSomething();
                }
            }.start();
        }
    }

    private static void doSomething(){
        try{
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (GetLockException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
