package org.daria.serebriakova.service.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConverterService {
    private Formatter formatter;

    /*
    @Qualifier annotation has higher priority over @Primary
     */
    @Autowired
    public ConverterService(@Qualifier("dotDateFormatter") Formatter formatter) {
        this.formatter = formatter;
    }

    public LocalDate convertWithFormatter(String input, Formatter formatter) {
        return formatter.format(input);
    }

    public LocalDate convert(String input) {
        return formatter.format(input);
    }
}
