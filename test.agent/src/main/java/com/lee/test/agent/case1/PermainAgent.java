package com.lee.test.agent.case1;

import java.lang.instrument.Instrumentation;

/**
 * @author cdlipan5
 * @create 2020-12-11 下午1:51
 **/
public class PermainAgent {
    private static Instrumentation INST;

    public static void premain(String agentArgs, Instrumentation inst) {
        INST = inst;
        process();
    }

    private static void process() {
        INST.addTransformer((loader, className, clazz, protectionDomain, byteCode) -> {
            System.out.printf("Process by ClassFileTransformer,target class = %s%n", className);
            return byteCode;
        });
    }
}
