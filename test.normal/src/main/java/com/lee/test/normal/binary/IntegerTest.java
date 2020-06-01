package com.lee.test.normal.binary;

import org.junit.jupiter.api.Test;

public class IntegerTest {

    public static void main(String[] args) {
        System.out.println(12>>2);
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(-6<<2));

//        IntegerTest test = new IntegerTest();
//        test.test1();
    }
    @Test
    public void test1() {
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(9));
        System.out.println(Integer.toBinaryString(10));
    }

    @Test
    public void test2() {
        String abc = "abc";
        char[] chars = abc.toCharArray();
        for (char aChar : chars) {
            System.out.print(Integer.toBinaryString(aChar));
            System.out.print(" ");
        }
    }
}
