package com.lee.test.spi;

/**
 * @author cdlipan5
 * @create 2020-06-30 上午11:36
 **/
public class LiLiangTaxRate implements TaxRate {
    @Override
    public String taxPayable() {
        return "李亮应交税费100000";
    }
}
