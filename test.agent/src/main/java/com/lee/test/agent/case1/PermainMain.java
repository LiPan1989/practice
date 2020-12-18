package com.lee.test.agent.case1;

/**
 * @author cdlipan5
 * @create 2020-12-18 下午1:54
 **/
public class PermainMain {
    public static void main(String[] args) {
        System.out.println("================Main Invoke================");
        HelloSample helloSample = new HelloSample();
        helloSample.sayHello();
    }
}
