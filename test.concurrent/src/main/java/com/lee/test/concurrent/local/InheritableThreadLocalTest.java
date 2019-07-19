package com.lee.test.concurrent.local;

/**
 * @author cdlipan5
 * @create 2019-07-18 13:42
 **/
public class InheritableThreadLocalTest {
    private static InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "initValue";
        }
    };

    public static void main(String[] args) {

    }
}
