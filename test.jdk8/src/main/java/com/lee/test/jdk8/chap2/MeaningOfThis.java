package com.lee.test.jdk8.chap2;

/**
 * Created by LIPAN on 2018/11/18.
 */
public class MeaningOfThis {
    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable runnable = () -> {
//          int value = 10;
            int val = 10;
            System.out.println(this.value);
        };

        Runnable runnable1 = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable1.run();
        runnable.run();

        Runnable runnable2 = () -> System.out.println("this is a test!");
        runnable.run();
        runnable.run();

        Thread thread = new Thread(() -> System.out.println("this is a test too!"));

        thread.start();
        thread.start();
    }

    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
