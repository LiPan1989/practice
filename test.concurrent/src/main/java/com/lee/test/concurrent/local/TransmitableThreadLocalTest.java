package com.lee.test.concurrent.local;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cdlipan5
 * @create 2019-07-18 13:44
 **/
public class TransmitableThreadLocalTest {
    private static TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "init TransmittableThreadLocal";
        }
    };

    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5));

    public static void main(String[] args) {

    }
}
