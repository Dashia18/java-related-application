package org.daria.serebriakova.dto;

public record BankAccountDto(Long id, int accountNumber, BankClientDto bankClient, long amount, boolean isPremium) {
}
