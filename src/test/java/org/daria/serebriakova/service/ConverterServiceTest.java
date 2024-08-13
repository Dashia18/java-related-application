package org.daria.serebriakova.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import org.daria.serebriakova.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

public class ConverterServiceTest extends AbstractIntegrationTest {

    @Test
    public void canCheckQualifierWork() {
        LocalDate expected = LocalDate.of(1987, Month.AUGUST, 8);

        LocalDate actual = converterService.convert("13.08.1987");

        assertThat(actual).isEqualTo(expected);
    }
}
