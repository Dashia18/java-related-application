package org.daria.serebriakova.util.functionalinterface;

@FunctionalInterface
public interface MathOperation {
    double operate(double a, double b);

    // Default method
    default double add(double a, double b) {
        return a + b;
    }

    // Static method
    static double subtract(double a, double b) {
        return a - b;
    }

    // Private method (Java 9 and later)
    private double multiply(double a, double b) {
        return a * b;
    }

    // Default method using the private method
    default double multiplyAndAdd(double a, double b, double c) {
        return add(multiply(a, b), c);
    }
}
