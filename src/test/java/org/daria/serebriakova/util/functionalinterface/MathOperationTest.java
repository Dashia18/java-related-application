package org.daria.serebriakova.util.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MathOperationTest {

    @Test
    public void canImplementAbstractMethod() {
        MathOperation division = (a, b) -> a / b;

        double actual = division.operate(10, 2);

        assertThat(actual).isEqualTo(5);
    }

    @Test
    public void canCallDefaultMethod() {
        MathOperation multiply = (a, b) -> a * b;

        double actual = multiply.add(multiply.operate(5, 2), 5);
        double actualUsingMultiplyAndAdd = multiply.multiplyAndAdd(5, 2, 5);

        assertThat(actual).isEqualTo(actualUsingMultiplyAndAdd);
    }

    @Test
    public void canCallStaticMethod() {
        double actual = MathOperation.subtract(10, 2);

        assertThat(actual).isEqualTo(8);
    }
}
