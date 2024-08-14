package org.dashia18.service.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import org.dashia18.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

public class ConverterServiceTest extends AbstractIntegrationTest {

    @Test
    public void canCheckQualifierWork() {
        LocalDate expected = LocalDate.of(1987, Month.AUGUST, 13);

        LocalDate actual = converterService.convert("13.08.1987");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void canChangeDependency() {
        LocalDate expected = LocalDate.of(1987, Month.AUGUST, 13);

        LocalDate actual = converterService.convertWithFormatter("13/08/1987", slashDateFormatter);

        assertThat(actual).isEqualTo(expected);
    }
}
