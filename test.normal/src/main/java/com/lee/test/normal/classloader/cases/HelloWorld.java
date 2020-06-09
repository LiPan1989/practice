package com.lee.test.normal.classloader.cases;

/**
 * @author cdlipan5
 * @create 2019-12-27 下午2:35
 **/
public class HelloWorld {

    static{
        System.out.println("Hello World!444");
    }

    private String str = "CCC";

    public String getStr() {
        return str;
    }
}
