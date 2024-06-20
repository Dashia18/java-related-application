package org.daria.serebriakova.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.mapper.BankAccountMapper;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.repo.BankAccountRepo;
import org.daria.serebriakova.util.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountDto getBankAccount(String id) {
        BankAccount bankAccount = bankAccountRepo.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Bank Account with id " + id + " is not found"));
        return bankAccountMapper.toDto(bankAccount);
    }

    public Page<BankAccountDto> getBankAccounts(String id, int offset, int limit,
                                                String sortField, String sortDirectionParam) {
        return new Page();
    }

    public BankAccountDto createBankAccount( BankAccountDto dto) {
        BankAccount bankAccount = bankAccountRepo.save(bankAccountMapper.fromDto(dto));
        return bankAccountMapper.toDto(bankAccount);
    }
}
