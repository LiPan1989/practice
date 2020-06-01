package com.lee.test.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        lock.lock();
        new Thread(new SignalThread()).start();
        System.out.println("主线程等待通知!");
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("主线程恢复执行!");
    }

    static class SignalThread implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.signal();
                System.out.println("子线程通知!");
            } finally {
                lock.unlock();
            }
        }
    }

}
