package org.dashia18.mapper;

import java.util.UUID;
import org.dashia18.dto.bank.MoneyTransferDto;
import org.dashia18.storage.model.bank.MoneyTransfer;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@org.mapstruct.Mapper
public interface MoneyTransferMapper {
    @Mapping(target = "sourceAccountNumber", expression = "java(dao.getFromAccount().toString())")
    @Mapping(target = "targetAccountNumber", expression = "java(dao.getToAccount().toString())")
    MoneyTransferDto toDto(MoneyTransfer dao);

}
