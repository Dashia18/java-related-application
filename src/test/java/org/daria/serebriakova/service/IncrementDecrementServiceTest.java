package org.daria.serebriakova.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IncrementDecrementServiceTest {
    @InjectMocks
    private IncrementDecrementService incrementDecrementService;

    @Test
    void canCalculatePreIncrementForOneValue() {
        int i = 1;
        int actual = incrementDecrementService.calculatePreIncrement(i);

        assertThat(actual).isEqualTo(2);
    }


    @Test
    void canCalculatePostIncrement() {
        int i = 1;
        int actual = incrementDecrementService.calculatePostIncrement(i);

        assertThat(actual).isEqualTo(1);
    }

    @Test
    //11 + 22 + 11 (a=12) + 22 (b =23) + 13 (a = 13) + 24 (b = 24) = 103
    void canCalculatePrePostIncrement() {
        int actual = incrementDecrementService.calculatePrePostIncrement(11, 22);

        assertThat(actual).isEqualTo(103);
    }

    @Test
    //11 (i = 12) + 13 (i =13) = 24
    void canCalculateSumPrePostIncrement() {
        int actual = incrementDecrementService.calculateSumPrePostIncrement(11);

        assertThat(actual).isEqualTo(24);
    }

    @Test
    //21 - 23 + 22 - 22 = -2
    void canCalculatePrePostIncrementDecrement() {
        int actual = incrementDecrementService.calculatePrePostIncrementDecrement(22, 22);

        assertThat(actual).isEqualTo(-2);
    }

    @Test
    //0 (i = 1) - 0 (i = 0) + 1 (i = 1) - 1 (i = 0) = 0
    void canCalculatePrePostIncrementDecrementForOneValue() {
        int actual = incrementDecrementService.calculatePrePostIncrementDecrement(0);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    //1 (i = 0) - 2 (i = 1) - 3 (i = 2) = -4
    void canCalculatePostDecrementForOneValue() {
        int actual = incrementDecrementService.calculatePostDecrement(1, 2, 3);

        assertThat(actual).isEqualTo(-4);
    }
}
