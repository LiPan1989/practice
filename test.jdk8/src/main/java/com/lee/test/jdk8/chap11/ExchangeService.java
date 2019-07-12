package com.lee.test.jdk8.chap11;

import static com.lee.test.jdk8.chap11.Util.delay;

/**
 * Created by LIPAN on 2018/12/8.
 */
public class ExchangeService {
    public enum Money {
        USD(1.0),
        EUR(1.35387),
        GBP(1.69715),
        CAD(.92106),
        MXN(.07683),
        ;
        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    public static double getRateWithDelay(Money source, Money destination) {
        delay();
        return destination.rate / source.rate;
    }
}
