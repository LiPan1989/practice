package com.lee.test.agent.case2;

import java.lang.instrument.Instrumentation;

/**
 * @author cdlipan5
 * @create 2020-12-11 下午1:55
 **/
public class AgentmainAgent {
    private static Instrumentation INST;

    public static void agentmain(String agentArgs, Instrumentation inst) {
        INST = inst;
        process();
    }

    private static void process() {
        INST.addTransformer((loader, className, clazz, protectionDomain, byteCode) -> {
            System.out.printf("Agentmain process by ClassFileTransformer,target class = %s%n", className);
            return byteCode;
        }, true);
        try {
            INST.retransformClasses(Class.forName("com.lee.test.agent.case2.AgentTargetSample"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
