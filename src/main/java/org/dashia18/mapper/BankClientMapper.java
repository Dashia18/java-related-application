package org.dashia18.mapper;

import org.dashia18.dto.BankClientDto;
import org.dashia18.storage.model.BankClient;
import org.mapstruct.Mapper;

@Mapper
public interface BankClientMapper {
    BankClientDto toDto(BankClient dao);

    BankClient fromDto(BankClientDto dto);
}
