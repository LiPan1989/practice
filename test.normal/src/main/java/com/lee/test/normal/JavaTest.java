package com.lee.test.normal;

/**
 * @author cdlipan5
 * @create 2020-04-07 下午4:51
 **/
public class JavaTest {
    public static void main(String[] args) {
        JavaTest t = new JavaTest();
        A a = t.new A();
        a.run();
        System.out.println(t);
    }

    public void run() {
        System.out.println("JavaTest");
        System.out.println(this);
        System.out.println(JavaTest.this);
    }

    class A {
        public void run() {
            JavaTest.this.run();
            System.out.println(JavaTest.this);
            System.out.println("A");
        }
    }
}

