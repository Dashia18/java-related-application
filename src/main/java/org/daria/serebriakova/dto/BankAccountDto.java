package org.daria.serebriakova.dto;

public record BankAccountDto(Long id, int accountNumber, String name, long amount, boolean isPremium) {
}
