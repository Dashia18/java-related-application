package org.daria.serebriakova.mapper;

import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.storage.model.BankAccount;
import org.mapstruct.Mapper;

@Mapper
public interface BankAccountMapper {

    BankAccountDto toDto(BankAccount bankAccount);
    BankAccount fromDto(BankAccountDto bankAccountDto);


}
