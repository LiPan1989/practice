package com.lee.test.concurrent.local;

import java.util.concurrent.TimeUnit;

public class A {
    static final ThreadLocal<String> threadParam = new ThreadLocal<>();

    static final InheritableThreadLocal<String> inheritableThreaParam = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception {
//        testMethod1();
        testMethod2();
    }

    private static void testMethod1() throws InterruptedException {
        while (true) {
            new Thread(() -> {
                threadParam.set("abc");
                System.out.println("t1:" + threadParam.get());
            }).start();

            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                System.out.println(threadParam.get());
            }).start();
        }
    }

    private static void testMethod2() throws InterruptedException {
        while (true) {
            new Thread(()-> {
                inheritableThreaParam.set("abc");
                System.out.println("t1:" + inheritableThreaParam.get());

                new Thread(()->{
                    System.out.println("t2:" + inheritableThreaParam.get());
                }).start();
            }).start();

            TimeUnit.SECONDS.sleep(1);

            new Thread(()->{
                System.out.println("t3:" + inheritableThreaParam.get());
            }).start();
        }
    }
}
