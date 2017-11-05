package com.lee.test.effective._34;

/**
 * Created by LIPAN on 2017/11/5.
 */
public enum BasicOperation implements Operation {
    PLUS("+") {
        public double applay(double x, double y) {
            return  x + y;
        }
    },
    MINUS("-") {
        public double applay(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double applay(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double applay(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    @Override
    public String toString() {
        return symbol;
    }

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }
}
