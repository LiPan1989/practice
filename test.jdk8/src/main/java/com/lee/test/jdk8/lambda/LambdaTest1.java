package com.lee.test.jdk8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LIPAN on 2017/9/23.
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        //传统的循环方式
        for (String player : players) {
            System.out.println("player = [" + player + "]");
        }

        System.out.println("==================================");

        //使用lambda表达式以及函数操作(function operation)
        players.forEach((player) -> System.out.println("player = [" + player + "]"));

        System.out.println("==================================");

        //在java8中使用双冒号操作符(double colon operater)
        players.forEach(System.out :: println);
    }
}
