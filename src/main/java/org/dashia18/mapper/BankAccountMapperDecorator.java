package org.dashia18.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.dashia18.dto.bank.BankAccountDto;
import org.dashia18.storage.model.bank.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BankAccountMapperDecorator implements BankAccountMapper {

    @Autowired
    @Qualifier("delegate")
    private BankAccountMapper delegateMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankClientMapper schemaElementMapper;

    @SneakyThrows
    @Override
    public BankAccountDto toDto(final BankAccount dao) {
        return delegateMapper.toDto(dao);
    }

    @Override
    public BankAccount fromDto(BankAccountDto dto) {

        return delegateMapper.fromDto(dto);
    }
}
