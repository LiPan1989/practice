package com.lee.test.concurrent.local;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {
    private static ThreadLocal<Map<String, String>> context;

    static {
        context = new ThreadLocal<>();
    }

    public static String get(String key) {
        Map<String, String> contextMap = context.get();
        if (contextMap != null) {
            return contextMap.get(key);
        }
        return null;
    }

    public static void set(String key, String value) {
        Map<String, String> contextMap = context.get();
        if (contextMap == null) {
            contextMap = new HashMap<>();
        }
        contextMap.put(key, value);
    }
}
