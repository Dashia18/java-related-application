package org.daria.serebriakova.service.converter;

import java.time.LocalDate;

public interface Formatter {
    LocalDate format(String input);
}
