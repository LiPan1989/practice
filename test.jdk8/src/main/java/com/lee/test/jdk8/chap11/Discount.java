package com.lee.test.jdk8.chap11;

import static com.lee.test.jdk8.chap11.Util.delay;
import static com.lee.test.jdk8.chap11.Util.format;

/**
 * Created by LIPAN on 2018/12/7.
 */
public class Discount {
    public enum Code {
        NONE(0),
        SLIVER(5),
        GOLD(10),
        PLATIUNM(15),
        DIAMOND(20),
        ;

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applayDiscount(Quote quote) {
        return quote.getShopName() + "price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);

    }
}
