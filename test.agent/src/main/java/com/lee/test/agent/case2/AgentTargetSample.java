package com.lee.test.agent.case2;

import com.lee.test.agent.case1.HelloSample;

import java.util.concurrent.TimeUnit;

/**
 * @author cdlipan5
 * @create 2020-12-11 下午1:59
 **/
public class AgentTargetSample {
    public static void main(String[] args) throws Exception {
        HelloSample sample = new HelloSample();
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            sample.sayHello();
        }
    }
}
