package com.lee.test.effective._31;

/**
 * Created by LIPAN on 2017/11/4.
 */
public enum Operation {
    PLUS("+"){
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double apply(double x, double y) {
            return x / y;
        }
    };
    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {

            System.out.printf("%s: %f %s %f = %f%n", op, x, inverse(op), y, op.apply(x, y));

        }
    }

    public static Operation inverse(Operation operation) {
        switch (operation) {
            case PLUS:return Operation.MINUS;
            case MINUS:return Operation.PLUS;
            case TIMES:return Operation.DIVIDE;
            case DIVIDE:return Operation.TIMES;
            default:
                throw new AssertionError("Unknown operation:" + operation);
        }
    }
}
