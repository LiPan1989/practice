package com.lee.test.concurrent.local;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {
    private static ThreadLocal<String> stringItl1 = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            System.out.println(Thread.currentThread().getName() + "initialize stringItl1 variable!");
            return "String init";
        }
    };

    private static ThreadLocal<String> stringItl2 = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            System.out.println(Thread.currentThread().getName() + " initialize stringItl2 variable!");
            return "String 2 init";
        }
    };

    private static ThreadLocal<StringBuffer> stringBufferItl1 = new InheritableThreadLocal<StringBuffer>() {
        @Override
        protected StringBuffer initialValue() {
            System.out.println(Thread.currentThread().getName() + " initialize stringBufferItl variable");
            return new StringBuffer("String buffer init");
        }
    };

    private static ThreadLocal<StringBuffer> getStringBufferItl12 = new InheritableThreadLocal<StringBuffer>(){
        @Override
        protected StringBuffer initialValue() {
            System.out.println(Thread.currentThread().getName() + " initialize stringBufferItl2 variable");
            return new StringBuffer("StringBuffer2 init");
        }
    };

    public static void main(String[] args) {
        stringItl1.set("Parent");
        stringBufferItl1.set(new StringBuffer("ParentBuffer"));
        System.out.println(Thread.currentThread().getName() + " first get stringItl1: " + stringItl1.get());
        System.out.println(Thread.currentThread().getName() + " first get stringBufferItl1: " + stringBufferItl1.get());

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " first get stringItl1: " + stringItl1.get());
                stringItl1.set(Thread.currentThread().getName() + " Child");

                System.out.println(Thread.currentThread().getName() + " get after set stringItl1: " + stringItl1.get());
//                stringItl2.
            }).start();
        }
    }

}
