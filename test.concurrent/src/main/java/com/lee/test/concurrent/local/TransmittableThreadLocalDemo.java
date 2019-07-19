package com.lee.test.concurrent.local;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransmittableThreadLocalDemo {
    private static ThreadLocal<String> itl = new TransmittableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "init";
        }
    };

    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5));

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
        new Thread(() -> {
            int index = 0;
            while (true) {
                sleep(1);
                itl.set(Thread.currentThread().getName() + ": " + index++);
                System.out.println(Thread.currentThread().getName() + "-->" + itl.get());
                executorService.execute(new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "-->" + itl.get());
                }, "Child Thread"));
            }
        }, "Father Thread").start();
    }

    private static void sleep(int howLong) {
        try {
            TimeUnit.SECONDS.sleep(howLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
