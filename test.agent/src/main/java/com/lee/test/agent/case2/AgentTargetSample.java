package com.lee.test.agent.case2;

import java.util.concurrent.TimeUnit;

/**
 * @author cdlipan5
 * @create 2020-12-11 下午1:59
 **/
public class AgentTargetSample {
    public void sayHello(String name) {
        System.out.printf("%s say hello!%n", name);
    }

    public static void main(String[] args) throws Exception {
        AgentTargetSample sample = new AgentTargetSample();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            sample.sayHello(Thread.currentThread().getName());
        }
    }
}
