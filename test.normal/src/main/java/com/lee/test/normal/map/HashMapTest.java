package com.lee.test.normal.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>(8);
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("10", 10);
        map.put("11", 11);
        System.out.println(map);
    }

    @Test
    public void test1() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("one", 10));
        personList.add(new Person("two", 20));
        personList.add(new Person("three", 30));
        //这两的name值重复
        personList.add(new Person("four", 40));
        personList.add(new Person("four", 45));

        System.out.println("利用Collectors.toMap去重:");
        //利用Collectors.toMap去重
        Map<String, Person> distinctMap = personList.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity(), (oldValue, newValue) -> oldValue));
        System.out.println(distinctMap);
    }

    @Test
    public void test2() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("one", 10));
        personList.add(new Person("two", 20));
        personList.add(new Person("three", 30));
        //这两的name值重复
        personList.add(new Person("four", 40));
        personList.add(new Person("four", 45));
        Set<String> collect = personList.stream().map(Person::getName).collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void test3() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("one", 10));
        personList.add(new Person("two", 20));
        personList.add(new Person("three", 30));
        //这两的name值重复
        personList.add(new Person("four", 40));
//        personList.add(new Person("four", 45));
        Map<String, Person> personMap = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
        System.out.println(personMap);
    }

    @Data
    @AllArgsConstructor
    private class Person {
        private String name;
        private int age;
    }

}
