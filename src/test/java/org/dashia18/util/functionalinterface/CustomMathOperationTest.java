package org.dashia18.util.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomMathOperationTest {

    @InjectMocks
    private CustomMathOperation customMathOperation;

    @Test
    public void canUseOverriddenDefaultMethod() {
        double actual = customMathOperation.operate(5, 7);

        assertThat(actual).isEqualTo(12.0);
    }
}
