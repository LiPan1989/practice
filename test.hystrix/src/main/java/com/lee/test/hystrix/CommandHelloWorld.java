package com.lee.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * Created by LIPAN on 2018/3/30.
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    protected String run() throws Exception {
        return "Hello " + this.name + " !";
    }

    public static void main(String[] args) {
        System.out.println(new CommandHelloWorld("Lee").execute());
    }
}
