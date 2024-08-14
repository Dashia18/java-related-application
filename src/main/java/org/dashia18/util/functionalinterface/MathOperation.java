package org.dashia18.util.functionalinterface;

/*
FunctionalInterface can implement non-functional interface but still has only one abstract method.
If it extends another functional interface, it inherits the abstract method from the parent interface.
 */
@FunctionalInterface
public interface MathOperation extends Operations {
    double operate(double a, double b);

    /*
    Can have multiple abstract methods if they are methods from java.lang.Object: toString, equals, and hashCode.
    These methods do not count towards the "single abstract method" rule.
     */
    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
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
