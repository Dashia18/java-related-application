package org.daria.serebriakova.dto;

import org.daria.serebriakova.storage.model.BankAccount;

public record MoneyTransferResponseDto(BankAccount sourceAccountNumber, BankAccount targetAccountNumber) {
}
