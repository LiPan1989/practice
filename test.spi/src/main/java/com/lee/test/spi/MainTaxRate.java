package com.lee.test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author cdlipan5
 * @create 2020-06-30 上午11:38
 **/
public class MainTaxRate {
    public static void main(String[] args) {
        ServiceLoader<TaxRate> serviceLoader = ServiceLoader.load(TaxRate.class);
        Iterator<TaxRate> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().taxPayable());
        }
    }
}
