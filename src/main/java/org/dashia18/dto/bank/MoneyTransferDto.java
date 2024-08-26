package org.dashia18.dto.bank;

import java.util.Objects;
import java.util.UUID;
import org.dashia18.storage.model.AuditableDto;
import org.dashia18.storage.model.audit.AuditableEntityType;

public record MoneyTransferDto(Long id, UUID sourceAccountNumber, UUID targetAccountNumber, long amount,
                               boolean performRollback) implements AuditableDto {
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public AuditableEntityType getType() {
        return AuditableEntityType.MONEY_TRANSFER;
    }
}
