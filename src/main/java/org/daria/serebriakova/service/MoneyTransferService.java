package org.daria.serebriakova.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.daria.serebriakova.dto.MoneyTransferDto;
import org.daria.serebriakova.dto.MoneyTransferResponseDto;
import org.daria.serebriakova.dto.TransferStatus;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.model.MoneyTransfer;
import org.daria.serebriakova.storage.repo.BankAccountRepo;
import org.daria.serebriakova.storage.repo.MoneyTransferRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoneyTransferService {
    private final MoneyTransferRepo moneyTransferRepo;
    private final BankAccountService bankAccountService;

    public MoneyTransferDto transferMoney(MoneyTransferDto dto) {
        try {
            BankAccount fromAccount = bankAccountService.getAccount(dto.sourceAccountNumber());
            long fromAmount = fromAccount.getAmount();
            BankAccount toAccount = bankAccountService.getAccount(dto.targetAccountNumber());
            long toAmount = toAccount.getAmount();
            long amount = dto.amount();

            Pair<Long, Long> updateAccounts =
                    bankAccountService.updateAccounts(fromAccount, toAccount, amount, dto.performRollback());

            MoneyTransfer moneyTransfer = MoneyTransfer.builder()
                    .fromAccount(fromAccount.getAccountNumber())
                    .fromAmount(fromAmount)
                    .toAccount(fromAccount.getAccountNumber())
                    .toAmount(toAmount)
                    .amount(amount)
                    .updatedFromAmount(updateAccounts.getLeft())
                    .updatedToAmount(updateAccounts.getRight())
                    .status(TransferStatus.SUCCESS)
                    .build();
            moneyTransferRepo.save(moneyTransfer);
            return dto;
        } catch (Exception e) {
            MoneyTransfer moneyTransfer = MoneyTransfer.builder()
                    .fromAccount(UUID.fromString(dto.sourceAccountNumber()))
                    .toAccount(UUID.fromString(dto.targetAccountNumber()))
                    .amount(dto.amount())
                    .status(TransferStatus.FAILED)
                    .error(e.getMessage())
                    .build();
            moneyTransferRepo.save(moneyTransfer);
            throw e;
        }
    }
}
