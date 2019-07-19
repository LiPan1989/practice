package com.lee.test.concurrent.local;

import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    private static ThreadLocal<String> count = ThreadLocal.withInitial(() -> "init");

    public static void main(String[] args) {

        new Thread(() -> {
            int index = 0;
            while (true){
                sleep(1);
                count.set(Thread.currentThread().getName()+ " " + index++);
                System.out.println(Thread.currentThread().getName() + "-->" + count.get());
            }

        },"Thread-0").start();
        new Thread(() -> {
            int index = 0;
            while (true){
                sleep(2);
                count.set(Thread.currentThread().getName()+ " " + (index++));
                System.out.println(Thread.currentThread().getName() + "-->" + count.get());
            }
        },"Thread-1").start();
    }

    private static void sleep(int howLong) {
        try {
            TimeUnit.SECONDS.sleep(howLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
