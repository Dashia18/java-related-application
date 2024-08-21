package org.dashia18.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.dashia18.dto.BankClientDto;
import org.dashia18.mapper.BankClientMapper;
import org.dashia18.storage.model.BankClient;
import org.dashia18.storage.repo.BankClientRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankClientService {
    private final BankClientRepo bankClientRepo;
    private final BankClientMapper bankClientMapper;

    public BankClientDto getBankClient(String id) {
        BankClient bankClient = bankClientRepo.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("Bank Account with id " + id + " is not found"));
        return bankClientMapper.toDto(bankClient);
    }

    public List<BankClientDto> getBankClients() {
        return bankClientRepo.findAll().stream()
                .map(bankClientMapper::toDto)
                .toList();
    }

    public BankClientDto updateBankClient(String id, BankClientDto dto) {
        String name = dto.name();
        String surname = dto.surname();
        BankClient clientForUpdate = bankClientRepo.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchElementException("There are no client: " + name + " " + surname));
        clientForUpdate.setCountry(dto.country());
        clientForUpdate.setStreet(dto.street());
        clientForUpdate.setBuilding(dto.building());
        clientForUpdate.setFlat(dto.flat());
        BankClient bankClient = bankClientRepo.save(clientForUpdate);
        return bankClientMapper.toDto(bankClient);
    }
}
