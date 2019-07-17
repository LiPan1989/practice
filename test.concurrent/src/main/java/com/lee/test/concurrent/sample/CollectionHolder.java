package com.lee.test.concurrent.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class CollectionHolder {

    private static ThreadLocal<String> collectionHolder = ThreadLocal.withInitial(() -> "ThreadLocal init");

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "InheritableThreadLocal init";
        }
    };

    public static String getConnection () {
        return collectionHolder.get();
    }

    public static String getInheritConnection() {
        return inheritableThreadLocal.get();
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + getInheritConnection());
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inheritableThreadLocal.remove();
            System.out.println(Thread.currentThread().getName() + " " + getInheritConnection());
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + " " + getInheritConnection());
    }
}
