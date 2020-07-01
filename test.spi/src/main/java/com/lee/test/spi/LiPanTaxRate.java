package com.lee.test.spi;

/**
 * @author cdlipan5
 * @create 2020-06-30 上午11:37
 **/
public class LiPanTaxRate implements TaxRate {
    @Override
    public String taxPayable() {
        return "李攀应交税费1000";
    }
}
