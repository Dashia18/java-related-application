package org.dashia18.service.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
@Primary defines a preference when multiple beans of the same type are present,
will be used unless otherwise indicated.
 */
@Component("slashDateFormatter")
@Primary
public class SlashDateFormatter implements Formatter {

    public LocalDate format(String input) {
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
