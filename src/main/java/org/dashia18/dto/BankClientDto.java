package org.dashia18.dto;

import java.time.OffsetDateTime;

public record BankClientDto(Long id, String name, String surname, OffsetDateTime dateOfBirth, String country,
                            String street, int building, int flat, OffsetDateTime updatedDate) {
    public static String UNKNOWN_STRING = "Unknown";
    public static int UNKNOWN_VALUE = 0;

    public static String getFullName(BankClientDto bankClientDto) {
        return bankClientDto.name + " " + bankClientDto.surname;
    }

    public BankClientDto(Long id, String name, String surname, OffsetDateTime dateOfBirth) {
        this(id, name, surname, dateOfBirth, UNKNOWN_STRING, UNKNOWN_STRING, UNKNOWN_VALUE, UNKNOWN_VALUE,
                OffsetDateTime.now());
    }
}
