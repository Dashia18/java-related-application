package org.dashia18.mapper;

import org.dashia18.dto.bank.MoneyTransferDto;
import org.dashia18.storage.model.bank.MoneyTransfer;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper
public interface MoneyTransferMapper {
    @Mapping(target = "sourceAccountNumber", expression = "java(dao.getFromAccount())")
    @Mapping(target = "targetAccountNumber", expression = "java(dao.getToAccount())")
    MoneyTransferDto toDto(MoneyTransfer dao);

    @Mapping(target = "fromAccount", expression = "java(dto.sourceAccountNumber())")
    @Mapping(target = "toAccount", expression = "java(dto.targetAccountNumber())")
    MoneyTransfer toDao(MoneyTransferDto dto);

}
