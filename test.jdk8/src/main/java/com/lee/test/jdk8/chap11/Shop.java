package com.lee.test.jdk8.chap11;

import java.util.Random;

import static com.lee.test.jdk8.chap11.Util.delay;
import static com.lee.test.jdk8.chap11.Util.format;

/**
 *
 * @author LIPAN
 * @date 2018/12/7
 */
public class Shop {
    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double calculatePrice(String product) {
        delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return this.name;
    }
}