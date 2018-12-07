package com.lee.test.jdk8.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by LIPAN on 2018/11/18.
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(160, "red"));
        List<Apple> greenApples = filterGreenApples(inventory);
        List<Apple> green = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        List<Apple> heavyApples = fileterHeavyApples(inventory);
        List<Apple> heavy = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);

        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        greenApples2 = filterApples(inventory, apple -> "green".equals(apple.getColor()));
        System.out.println(greenApples2);

        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() > 150 || "brown".equals(a.getColor()));
        System.out.println(weirdApples);

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterRedApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if ("red".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> fileterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
}
