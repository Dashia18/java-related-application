package org.daria.serebriakova.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.dto.MoneyTransferDto;
import org.daria.serebriakova.mapper.BankAccountMapper;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.repo.BankAccountRepo;
import org.daria.serebriakova.storage.repo.MoneyTransferRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final MoneyTransferRepo moneyTransferRepo;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountDto getBankAccount(String id) {
        BankAccount bankAccount = getAccount(id);
        return bankAccountMapper.toDto(bankAccount);
    }

    public BankAccount getAccount(String id) {
        return bankAccountRepo.findBankAccountByAccountNumber(UUID.fromString(id))
                .orElseThrow(() -> new NoSuchElementException("Bank Account with id " + id + " is not found"));
    }

    public List<BankAccountDto> getBankAccounts() {
        return bankAccountRepo.findAll().stream()
                .map(bankAccountMapper::toDto)
                .toList();
    }

    public BankAccountDto createBankAccount(BankAccountDto dto) {
        BankAccount accountToCreate = bankAccountMapper.fromDto(dto)
                .setAccountNumber(UUID.randomUUID());
        BankAccount bankAccount = bankAccountRepo.save(accountToCreate);
        return bankAccountMapper.toDto(bankAccount);
    }

    @Transactional
    public Pair<Long, Long> updateAccounts(BankAccount fromAccount, BankAccount toAccount, long amount,
                                           boolean performRollback) {
        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);

        BankAccount source = bankAccountRepo.save(fromAccount);
        BankAccount target = bankAccountRepo.save(toAccount);
        if (performRollback) {
            throw new IllegalStateException("Perform rollback, transfer is failed");
        }
        return Pair.of(source.getAmount(), target.getAmount());
    }
}
