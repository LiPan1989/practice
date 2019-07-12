package com.lee.test.concurrent.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    private static ExecutorService parentExcutor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

    }
}

class ThreadA implements Runnable {

    @Override
    public void run() {

    }
}
