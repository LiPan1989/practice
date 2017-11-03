package com.lee.test.effective.enums;

/**
 * Created by LIPAN on 2017/11/3.
 */
public class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for(Planet p : Planet.values()) {
            System.out.printf("Weigth on %s is %f%n", p, p.surfaceWeight(mass));
        }
    }
}
