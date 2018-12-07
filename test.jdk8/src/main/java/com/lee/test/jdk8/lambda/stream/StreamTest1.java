package com.lee.test.jdk8.lambda.stream;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by LIPAN on 2018/11/28.
 */
public class StreamTest1 {
    public static void main(String[] args) {
        Map<Integer, Boolean> data = init().entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(data);

        Map<Integer, Boolean> integerBooleanMap = Optional.of(init()).map(v -> v.entrySet()
                .stream()
                .filter(m -> !m.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))).orElse(null);
        System.out.println(integerBooleanMap);

        long trueCount = init().entrySet().stream().filter(Map.Entry::getValue).count();
        long falseCount = init().entrySet().stream().filter(e -> !e.getValue()).count();

        System.out.println("trueCount:" + trueCount + ", falseCount:" + falseCount);

        Stream<String> stream = Stream.of("I", "Love", "You", "Too", "!");
        List<String> list = stream.collect(Collectors.toList());
        System.out.println(list);

        System.out.println(stream.collect(Collectors.joining(", ", "[", "]")));

    }

    private static Map<Integer, Boolean> init() {
        Map<Integer, Boolean> result = Maps.newHashMap();
        result.put(1,true);
        result.put(2, false);
        result.put(3, false);
        result.put(4, true);
        result.put(5, false);
        result.put(6, true);
        result.put(7, true);
        result.put(8, false);
        result.put(9, true);
        return result;
    }

    private static boolean checkValue(Object object) {
       return (boolean) Optional.ofNullable(object).filter(obj -> obj instanceof String && "".equals(obj)).orElse(false);
    }
}
