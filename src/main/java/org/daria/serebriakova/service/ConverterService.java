package org.daria.serebriakova.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConverterService {
    private final Formatter formatter;

    @Autowired
    public ConverterService(@Qualifier("slashDateFormatter") Formatter formatter) {
        this.formatter = formatter;
    }

    public LocalDate convert(String input) {
        return formatter.format(input);
    }

    public interface Formatter {
        LocalDate format(String input);
    }

    @Component("slashDateFormatter")
    public class SlashDateFormatter implements Formatter {

        public LocalDate format(String input) {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    }

    @Component("dotDAteFormatter")
    public class DotDsteFormatter implements Formatter {

        public LocalDate format(String input) {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }
}
