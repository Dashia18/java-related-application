package org.daria.serebriakova.controller;

import static org.daria.serebriakova.util.Constants.COMMON_API_URI;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.daria.serebriakova.service.concurrent.ConcurrentService;
import org.daria.serebriakova.service.concurrent.ReentrantLockService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = COMMON_API_URI + ConcurrentController.CONCURRENT_API_URI)
public class ConcurrentController {
    //TODO: CRUD operations: naming rules, Rest designApi, singular/plural in naming, status codes
    public static final String CONCURRENT_API_URI = "concurrent/";
    public static final String REENTRANT_LOCK_API_URI = "reentrant/lock/";

    private final ReentrantLockService reentrantLockService;
    private final ConcurrentService concurrentService;

    @GetMapping(value = REENTRANT_LOCK_API_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> get() {
        log.info("A request to try reentered lock has been received");
        return concurrentService.getThreasRusLogs();
    }
}
