package com.lee.test.effective._34;

/**
 * Created by LIPAN on 2017/11/5.
 */
public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double applay(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double applay(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
