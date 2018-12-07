package com.lee.test.jdk8.lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author LIPAN
 *
 */
public class MainTest {
	public static void main(String args[]) {
		List<Long> list = Lists.newArrayList();
		Random random = new Random(100);
		for (int i = 0; i < 100; i++) {
			list.add(random.nextLong());
		}

		List<Long> collect = list.stream().map(a -> a % 2).collect(Collectors.toList());
		System.out.println(collect);
	}
}
