package com.lee.test.normal.interfaces;

/**
 * @author cdlipan5
 * @create 2020-03-31 下午5:18
 **/
public class AImpl implements A {
    @Override
    public void mehtod(String aaa) {
//        System.out.println("===============" +aaa+ "===============");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            A a = new AImpl();
            a.mehtod(""+i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
