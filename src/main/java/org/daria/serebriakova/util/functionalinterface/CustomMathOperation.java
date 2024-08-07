package org.daria.serebriakova.util.functionalinterface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/*
When a class implements this functional interface, it must provide an implementation for the operate method.
It can also override the methods from java.lang.Object if needed.
 */
@Slf4j
@Component
public class CustomMathOperation implements MathOperation {
    @Override
    public double operate(double a, double b) {
        log.info("Performing operation ... ");
        return a > b ? a : add(a, b);
    }

    @Override
    public boolean equals(Object obj) {
        // Custom implementation for equals
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomMathOperation that = (CustomMathOperation) obj;
        return true; // Simplified for demonstration
    }

    @Override
    public int hashCode() {
        // Custom implementation for hashCode
        return 42; // Simplified for demonstration
    }

    /*
    Default method in functional interface can be overridden
    */
    @Override
    public double add(double a, double b) {
        log.info("Performing add operation ... ");
        return MathOperation.super.add(a, b);
    }
}
