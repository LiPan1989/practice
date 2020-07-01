package com.lee.test.spi;

/**
 * @author cdlipan5
 * @create 2020-06-30 上午11:36
 **/
public class ZhouDeDongTaxRate implements TaxRate {
    @Override
    public String taxPayable() {
        return "周德东应交税费50000";
    }
}
