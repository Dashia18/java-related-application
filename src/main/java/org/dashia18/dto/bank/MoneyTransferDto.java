package org.dashia18.dto.bank;


import java.util.Objects;
import org.dashia18.storage.model.Auditable;

public record MoneyTransferDto(Long id, String sourceAccountNumber, String targetAccountNumber, long amount,
                               boolean performRollback) {
    /**
     * Verification of input data for creation the record class, run for default constructor.
     */
    public MoneyTransferDto {
        Objects.requireNonNull(sourceAccountNumber, "Source account has not specified");
        Objects.requireNonNull(targetAccountNumber, "Target account has not specified");
        if (amount < 1) {
            throw new IllegalArgumentException("Amount of transfer should be more than 0");
        }
    }
}