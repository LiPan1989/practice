package com.lee.test.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest3 {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadDemo(lock1, lock2));
        Thread thread2 = new Thread(new ThreadDemo(lock1, lock2));

        thread1.start();
        thread2.start();
    }

    static class ThreadDemo implements Runnable {
        private Lock firstLock;
        private Lock secondLock;

        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            try {
                while (!lock1.tryLock()) {
                    TimeUnit.MILLISECONDS.sleep(10);
                }

                while (!lock2.tryLock()) {
                    lock1.unlock();
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
                System.out.println(Thread.currentThread().getName() + "正常结束!");
            }
        }
    }

}
