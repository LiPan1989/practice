package com.lee.test.agent;

import java.lang.instrument.Instrumentation;

public class FirstAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs: " + agentArgs);
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            System.out.println("premain load class: " + className);
            return classfileBuffer;
        }, true);
    }
}
