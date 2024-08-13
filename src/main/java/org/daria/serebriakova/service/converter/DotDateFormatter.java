package org.daria.serebriakova.service.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component("dotDateFormatter")
public class DotDateFormatter implements Formatter {

    public LocalDate format(String input) {
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
