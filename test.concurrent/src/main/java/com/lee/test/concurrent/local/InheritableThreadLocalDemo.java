package com.lee.test.concurrent.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class InheritableThreadLocalDemo {
    private static ThreadLocal<String> itl = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "init";
        }
    };

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
//        simpleThread();
        poolThread();
    }

    private static void simpleThread() {
        new Thread(() -> {
            int index = 0;
            while (true) {
                sleep(1);
                itl.set(Thread.currentThread().getName() + ": " + index++);
                System.out.println(Thread.currentThread().getName() + "-->" + itl.get());
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "-->" + itl.get());
                }, "Child Thread").start();
            }
        }, "Father Thread").start();
    }

    private static void poolThread() {
        AtomicInteger index = new AtomicInteger();
        while (true) {
            int andIncrement = index.getAndIncrement();
            sleep(1);
            new Thread(() -> {
                itl.set(Thread.currentThread().getName() + ": " + andIncrement);
                System.out.println(Thread.currentThread().getName() + "-->" + itl.get());
                executorService.execute(new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "-->" + itl.get());
                }, "Child Thread"));
            }, "Father Thread" + andIncrement).start();
        }
    }

    private static void sleep(int howLong) {
        try {
            TimeUnit.SECONDS.sleep(howLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
