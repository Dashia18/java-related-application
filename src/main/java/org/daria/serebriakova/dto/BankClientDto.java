package org.daria.serebriakova.dto;

public record BankClientDto(Long id, String name, String surname, String address) {
    public static String UNKNOWN_ADDRESS = "Unknown";

    public static String getFullName(BankClientDto bankClientDto) {
        return bankClientDto.name + " " + bankClientDto.surname;
    }

    public BankClientDto(Long id, String name, String surname) {
        this(id, name, surname, UNKNOWN_ADDRESS);
    }
}
