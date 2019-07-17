package com.lee.test.concurrent.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.*;

public class B {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static final InheritableThreadLocal<String> inheritableThreadParam = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception {
//        testMethod1();
        testMethod2();
    }

    private static void testMethod1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        while (true) {
            executorService.execute(() -> {
                threadLocal.set("abc");
                out.println("t1:" + threadLocal.get());
                threadLocal.remove();
            });

            SECONDS.sleep(1);
            executorService.execute(() -> {
                System.out.println("t2:" + threadLocal.get());
            });
        }
    }

    private static void testMethod2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        while (true) {
            Thread t1 = new Thread(() -> {
                inheritableThreadParam.set("abc");
                out.println("t1:" + inheritableThreadParam.get() + "  invokeThread:" + Thread.currentThread().getName());
                Thread t2 = new Thread(() -> out.println("t2:" + inheritableThreadParam.get() + "  invokeThread:" + Thread.currentThread().getName()));
                executorService.execute(t2);

                Thread t3 = new Thread(() -> {
                    out.println("t3:" + inheritableThreadParam.get() + "  invokeThread:" + Thread.currentThread().getName());
                });
                executorService.execute(t3);
            });
            executorService.execute(t1);

            SECONDS.sleep(1);
            Thread t4 = new Thread(() -> {
                inheritableThreadParam.set("CBA");
                out.println("t4:" + inheritableThreadParam.get() + "  invokeThread:" + Thread.currentThread().getName());
            });

            executorService.execute(t4);
        }

    }
}
