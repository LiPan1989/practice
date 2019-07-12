package com.lee.test.concurrent.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class B {
    static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static final InheritableThreadLocal<String> inheritableThreaParam = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception {
//        testMethod1();
        testMethod2();
    }

    private static void testMethod1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        while (true) {
            executorService.execute(() -> {
                threadLocal.set("abc");
                System.out.println("t1:" + threadLocal.get());
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
                inheritableThreaParam.set("abc");
                System.out.println("t1:" + inheritableThreaParam.get() + "  invokeThread:" + Thread.currentThread().getName());
                Thread t2 = new Thread(() -> {
                    System.out.println("t2:" + inheritableThreaParam.get() + "  invokeThread:" + Thread.currentThread().getName());
                });
                executorService.execute(t2);

                Thread t3 = new Thread(() -> {
                    System.out.println("t3:" + inheritableThreaParam.get() + "  invokeThread:" + Thread.currentThread().getName());
                });
                executorService.execute(t3);
            });
            executorService.execute(t1);

            SECONDS.sleep(1);
            Thread t4 = new Thread(() -> {
               inheritableThreaParam.set("CBA");
                System.out.println("t4:" + inheritableThreaParam.get() + "  invokeThread:" + Thread.currentThread().getName());
            });

            executorService.execute(t4);
        }

    }
}
