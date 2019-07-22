package com.lee.test.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTransfromer implements ClassFileTransformer {
    private static final String prefix = "\nlong startTime = System.currentTimeMillis();\n";
    private static final String postfix = "\nlong endTime = System.currentTimeMillis();\n";

    private static final Map<String, List<String>> methodMap = new HashMap<>();

    public MyTransfromer() {
        add("");
        add("");
    }

    private void add(String methodStr) {
        String className = methodStr.substring(0, methodStr.lastIndexOf("."));
        String methodName = methodStr.substring(methodStr.lastIndexOf("." + 1));
        List<String> list = methodMap.get(className);
        if (list == null) {
            list = new ArrayList<>();
            methodMap.put(className, list);
        }
        list.add(methodName);
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        return new byte[0];
    }
}
