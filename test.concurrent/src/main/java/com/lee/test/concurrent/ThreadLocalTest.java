package com.lee.test.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    private final static ExecutorService outerExecutorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                final String threadName = "threadLpLocal: " + index;
                ThreadLocalHolder.set(threadName);
                int count = 100;
                CountDownLatch countDownLatch = new CountDownLatch(count);
                for (int j = 0; j < count; j++) {
                    outerExecutorService.execute(new InnerClass(threadName, countDownLatch));
                }
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ThreadLocalHolder.remove();
            });
        }
        System.out.println("--------------------------end--------------------------");
        executorService.shutdown();
//        System.exit(-1);
    }

    static class InnerClass implements Runnable {
        private final String taskName;
        private final CountDownLatch countDownLatch;

        public InnerClass(String taskName, CountDownLatch countDownLatch) {
            this.taskName = taskName;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            String threadLocalName = ThreadLocalHolder.get();
//                        if(threadLocalName != null && !threadName.equals(threadLocalName)){
            System.out.println(taskName + "--->" + (threadLocalName));
//                        }
            countDownLatch.countDown();
            try {
                TimeUnit.MILLISECONDS.sleep(new Random(5000).nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadLocalHolder {
    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void set(String name) {
        threadLocal.set(name);
    }
    public static String get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
