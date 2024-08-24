package org.dashia18.controller;

import static com.google.common.base.Preconditions.checkArgument;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.dashia18.dto.bank.BankAccountDto;
import org.dashia18.dto.bank.MoneyTransferDto;
import org.dashia18.service.BankAccountService;
import org.dashia18.service.MoneyTransferService;
import org.dashia18.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.COMMON_API_URI + Constants.BANK_API_URI)
public class BankAccountController {
    //TODO: CRUD operations: naming rules, Rest designApi, singular/plural in naming, status codes
    public static final String BANK_ACCOUNT_API_URI = "account/";
    public static final String BANK_ACCOUNTS_API_URI = "accounts/";

    private final BankAccountService bankAccountService;
    private final MoneyTransferService moneyTransferService;

    @GetMapping(value = BANK_ACCOUNT_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDto get(
            @RequestParam String accountNumber) {

        log.info("A request to retrieve bank account for id {} has been received", accountNumber);

        return bankAccountService.getBankAccount(accountNumber);
    }

    @GetMapping(value = BANK_ACCOUNTS_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankAccountDto> getAll() {
        log.info("A request to retrieve all accounts has been received");

        return bankAccountService.getBankAccounts();
    }

    @PostMapping(value = BANK_ACCOUNT_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountDto post(@RequestBody @Valid final BankAccountDto dto) {
        checkArgument(dto.id() == null, "It is forbidden for a client to specify account id");

        log.info("A request to create bank account has been received");

        return bankAccountService.createBankAccount(dto);
    }

    @PutMapping(value = BANK_ACCOUNT_API_URI + "transfer/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MoneyTransferDto transfer(@RequestBody @Valid final MoneyTransferDto dto) {
        log.info("A request to create bank account has been received");
        var i = 1;
        val j = 1;
        return moneyTransferService.transferMoney(dto);
    }
}
