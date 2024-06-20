package org.daria.serebriakova.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static org.daria.serebriakova.util.Constants.DEFAULT_SORTING_DIRECTION;
import static org.daria.serebriakova.util.Constants.DEFAULT_SORTING_FIELD;
import static org.daria.serebriakova.util.Constants.SORT_DIRECTION_PARAM;
import static org.daria.serebriakova.util.Constants.SORT_FIELD_PARAM;
import static org.daria.serebriakova.util.Page.DEFAULT_LIMIT;
import static org.daria.serebriakova.util.Page.DEFAULT_OFFSET;
import static org.daria.serebriakova.util.Page.LIMIT_PARAM;
import static org.daria.serebriakova.util.Page.OFFSET_PARAM;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.daria.serebriakova.dto.BankAccountDto;
import org.daria.serebriakova.service.BankAccountService;
import org.daria.serebriakova.storage.model.BankAccount;
import org.daria.serebriakova.storage.repo.BankAccountRepo;
import org.daria.serebriakova.util.Constants;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = BankAccountController.BANK_ACCOUNT_API_URI)
public class BankAccountController {
    //TODO: CRUD operations: naming rules, Rest designApi, singular/plural in naming, status codes
    public static final String BANK_ACCOUNT_API_URI = "bank/account/";

    private final BankAccountService bankAccountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BankAccountDto get(
            @RequestParam(name = DEFAULT_SORTING_FIELD, required = false) String id) {

        log.info("A request to retrieve bank account for id {} has been received", id);

        return bankAccountService.getBankAccount(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountDto post(@RequestBody @Valid final BankAccountDto dto) {
        checkArgument(dto.id() == null, "It is forbidden for a client to specify ADF pipeline id");

        log.info("A request to create bank account has been received");

        return bankAccountService.createBankAccount(dto);
    }

}
