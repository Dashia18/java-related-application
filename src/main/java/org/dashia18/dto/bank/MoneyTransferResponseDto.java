package org.dashia18.dto.bank;

import org.dashia18.storage.model.bank.BankAccount;

public record MoneyTransferResponseDto(BankAccount sourceAccountNumber, BankAccount targetAccountNumber) {
}
