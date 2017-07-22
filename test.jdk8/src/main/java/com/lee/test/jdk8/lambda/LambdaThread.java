package com.lee.test.jdk8.lambda;

import java.util.concurrent.TimeUnit;

public class LambdaThread {
	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> System.out.println("Hello Lambda!")).start();
		TimeUnit.SECONDS.sleep(10);
	}
}
