package com.lee.test.utils;

public class StackTest {
    private int count = 0;

    public void testStack(int a, int b) {
        count++;
        int c, d = 4;
        long e = Long.MAX_VALUE;
//        System.out.println(count);
        testStack(a, b);
    }

    public void test() {
        try {
            testStack( 1, 2);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            System.out.println("stack height: " + count);
        }
    }

    public static void main(String[] args) {
        new StackTest().test();
    }

}
