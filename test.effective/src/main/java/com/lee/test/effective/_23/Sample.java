package com.lee.test.effective._23;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIPAN on 2017/11/8.
 */
public class Sample {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings, new Integer(23));
        String s = strings.get(0);
    }

    private static void unsafeAdd(List list, Object object) {
        list.add(object);
    }

    private static void unsafeAdd2(List<Object> list, Object object) {
        list.add(object);
    }
}
