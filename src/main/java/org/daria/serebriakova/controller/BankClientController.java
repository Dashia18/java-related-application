package org.daria.serebriakova.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static org.daria.serebriakova.util.Constants.COMMON_API_URI;
import static org.daria.serebriakova.util.Constants.DEFAULT_SORTING_FIELD;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.daria.serebriakova.dto.BankClientDto;
import org.daria.serebriakova.service.BankClientService;
import org.daria.serebriakova.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = COMMON_API_URI + Constants.BANK_API_URI)
public class BankClientController {
    //TODO: CRUD operations: naming rules, Rest designApi, singular/plural in naming, status codes
    public static final String CLIENT_API_URI = "client/";
    public static final String CLIENTS_API_URI = "clients/";

    private final BankClientService bankAccountService;

    @GetMapping(value = CLIENT_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public BankClientDto get(
            @RequestParam(name = DEFAULT_SORTING_FIELD, required = false) String id) {

        log.info("A request to retrieve client for id {} has been received", id);

        return bankAccountService.getBankClient(id);
    }

    @GetMapping(value = CLIENTS_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BankClientDto> getAll() {
        log.info("A request to retrieve all clients has been received");

        return bankAccountService.getBankClients();
    }

    @PutMapping(value = CLIENT_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BankClientDto put(
            @RequestParam(name = DEFAULT_SORTING_FIELD, required = false) String id,
            @RequestBody @Valid final BankClientDto dto) {
        log.info("A request to update bank client has been received");

        return bankAccountService.updateBankClient(id, dto);
    }
}
