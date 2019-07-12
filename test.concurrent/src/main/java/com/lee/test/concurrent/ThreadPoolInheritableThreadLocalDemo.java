package com.lee.test.concurrent;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolInheritableThreadLocalDemo {

    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    static ExecutorService pool = Executors.newFixedThreadPool(2);

//    private static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
//    private static ExecutorService pool =  TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(3));

    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            int j = i;
            pool.execute(new Thread(() -> {
                threadLocal.set("superWorld"+j);

                pool.execute(() -> System.out.println(Thread.currentThread().getName()  +
                        " : " +
                        ThreadPoolInheritableThreadLocalDemo.threadLocal.get()));
            }));
        }
    }

}
