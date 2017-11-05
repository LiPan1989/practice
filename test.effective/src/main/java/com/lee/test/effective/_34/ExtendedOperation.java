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

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        test(ExtendedOperation.class, x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> optSet, double x, double y) {
        for (Operation op : optSet.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.applay(x, y));
        }
    }
}
