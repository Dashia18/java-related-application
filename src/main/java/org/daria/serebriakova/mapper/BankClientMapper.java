package org.daria.serebriakova.mapper;

import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.dto.BankClientDto;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.model.BankClient;
import org.mapstruct.Mapper;

@Mapper
public interface BankClientMapper {
    BankClientDto toDto(BankClient dao);

    BankClient fromDto(BankClientDto dto);
}
