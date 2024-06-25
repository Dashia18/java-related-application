package org.daria.serebriakova.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.daria.serebriakova.dto.BankClientDto;
import org.daria.serebriakova.mapper.BankClientMapper;
import org.daria.serebriakova.storage.model.BankClient;
import org.daria.serebriakova.storage.repo.BankClientRepo;
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

    public BankClientDto updateBankClient(BankClientDto dto) {
        String name = dto.name();
        String surname = dto.surname();
        BankClient clientForUpdate = bankClientRepo.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new NoSuchElementException("There are no client: " + name + " " + surname));
        clientForUpdate.setAddress(dto.address());
        BankClient bankClient = bankClientRepo.save(clientForUpdate);
        return bankClientMapper.toDto(bankClient);
    }
}
