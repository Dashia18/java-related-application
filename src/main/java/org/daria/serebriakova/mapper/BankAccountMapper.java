package org.daria.serebriakova.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.storage.model.BankAccount;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {BankClientMapper.class}, injectionStrategy = CONSTRUCTOR)
@DecoratedWith(BankAccountMapperDecorator.class)
public interface BankAccountMapper {
    BankAccountDto toDto(BankAccount dao);

    BankAccount fromDto(BankAccountDto dto);
}
