package org.dashia18.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

import org.dashia18.dto.bank.BankAccountDto;
import org.dashia18.storage.model.bank.BankAccount;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {BankClientMapper.class}, injectionStrategy = CONSTRUCTOR)
@DecoratedWith(BankAccountMapperDecorator.class)
public interface BankAccountMapper {
    BankAccountDto toDto(BankAccount dao);

    BankAccount fromDto(BankAccountDto dto);
}
