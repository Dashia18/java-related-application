package org.daria.serebriakova.util.functionalinterface;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomMathOperation implements MathOperation {
    @Override
    public double operate(double a, double b) {
        log.info("Performing operation ... ");
        return a > b ? a : add(a, b);
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
