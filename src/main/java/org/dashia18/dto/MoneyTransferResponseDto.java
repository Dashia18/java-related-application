package org.dashia18.dto;

import org.dashia18.storage.model.BankAccount;

public record MoneyTransferResponseDto(BankAccount sourceAccountNumber, BankAccount targetAccountNumber) {
}
