package com.lee.test.normal.classloader;

import java.net.URL;

/**
 * @author cdlipan5
 * @create 2019-12-25 上午10:24
 **/
public class BootStrapTest {
    public static void main(String[] args) {
        ClassLoader classLoader = BootStrapTest.class.getClassLoader();
        System.out.println(classLoader);
    }
}
