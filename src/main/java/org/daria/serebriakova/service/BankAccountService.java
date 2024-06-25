package org.daria.serebriakova.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.mapper.BankAccountMapper;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.repo.BankAccountRepo;
import org.daria.serebriakova.util.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountDto getBankAccount(String id) {
        BankAccount bankAccount = bankAccountRepo.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Bank Account with id " + id + " is not found"));
        return bankAccountMapper.toDto(bankAccount);
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
}
