package com.lee.test.effective._31;

/**
 * Created by LIPAN on 2017/11/3.
 */
public enum Planet {
    MERCURY(3.202e+13, 2.439e6),
    VENUS(4.689e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6);
    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G + mass / (radius * radius);
    }

    public double mass(){
        return this.mass;
    }

    public double radius() {
        return this.radius;
    }

    public double surfaceGravity(){
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }

}
