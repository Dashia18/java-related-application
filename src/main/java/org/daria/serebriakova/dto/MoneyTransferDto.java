package org.daria.serebriakova.dto;


public record MoneyTransferDto(String sourceAccountNumber, String targetAccountNumber, long amount,
                               boolean performRollback) {
}
