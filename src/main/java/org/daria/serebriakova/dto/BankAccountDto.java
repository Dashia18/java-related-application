package org.daria.serebriakova.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BankAccountDto(Long id, UUID accountNumber, BankClientDto bankClient, long amount, boolean isPremium,
                             OffsetDateTime createdDate, OffsetDateTime updatedDate) {
}
