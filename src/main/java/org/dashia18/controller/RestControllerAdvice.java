package org.dashia18.controller;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class RestControllerAdvice {

    @ResponseStatus(CONFLICT)
    @ResponseBody
    @ExceptionHandler(IllegalStateException.class)
    public ErrorDto handleIllegalStateException(IllegalStateException ise, ServletWebRequest request) {
        return buildResponseEntity(ise, ise.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorDto handleNoSuchElementException(NoSuchElementException nse, ServletWebRequest request) {
        return buildResponseEntity(nse, nse.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorDto handleNumberFormatException(MethodArgumentTypeMismatchException nse, ServletWebRequest request) {
        return buildResponseEntity(nse, nse.getMessage());
    }

    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ErrorDto handleHttpClientErrorExceptionForbidden(HttpClientErrorException.Forbidden forbidden,
                                                            ServletWebRequest request) {
        return buildResponseEntity(forbidden, forbidden.getMessage());
    }

    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorDto handleAccessDeniedException(AccessDeniedException ade, ServletWebRequest request) {
        return buildResponseEntity(ade, ade.getMessage());
    }

    @ResponseStatus(UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ErrorDto handleHttpClientErrorExceptionUnauthorized(HttpClientErrorException.Unauthorized unauthorized,
                                                               ServletWebRequest request) {
        return buildResponseEntity(unauthorized, unauthorized.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDto handleIllegalArgumentException(IllegalArgumentException iae, ServletWebRequest request) {
        return buildResponseEntity(iae, iae.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException cve, ServletWebRequest request) {
        return buildResponseEntity(cve, cve.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorDto handleConstraintViolationException(DataIntegrityViolationException dve, ServletWebRequest request) {
        val dive = replaceErrMsgForConstraintViolationException(dve);
        return buildResponseEntity(dive, dive.getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDto handleHttpMessageNotReadableException(HttpMessageNotReadableException mnre,
                                                          ServletWebRequest request) {
        return buildResponseEntity(mnre, prepareErrMsg(mnre));
    }

    private static String prepareErrMsg(HttpMessageNotReadableException nmre) {
        final String nestedExceptionMessageSeparator = "nested";
        return ofNullable(nmre)
                .map(Throwable::getMessage)
                .filter(msg -> msg.contains(nestedExceptionMessageSeparator))
                .map(msg -> msg.substring(0, msg.indexOf(nestedExceptionMessageSeparator)))
                .orElse(Objects.requireNonNull(nmre).getMessage());
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException manve,
                                                          ServletWebRequest request) {
        return buildResponseEntity(manve,
                manve.getAllErrors()
                        .stream()
                        .map(RestControllerAdvice::message)
                        .collect(Collectors.joining("; "))
        );
    }

    private static String message(final ObjectError error) {
        if (error instanceof FieldError fieldError) {
            return fieldError.getField() + ": " + fieldError.getDefaultMessage();
        }

        return error.getDefaultMessage();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public ErrorDto handleDataAccessException(DataAccessException dae, ServletWebRequest request) {
        return buildResponseEntity(dae, getReason(dae));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(RestClientException.class)
    public ErrorDto handleDataAccessException(RestClientException rce, ServletWebRequest request) {
        return buildResponseEntity(rce, getReason(rce));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorDto handleException(Exception e, ServletWebRequest request) {
        return buildResponseEntity(e, e.getMessage());
    }

    private ErrorDto buildResponseEntity(final Throwable throwable, final String errorMessage) {
        log.error("Http request processing failed due to {}", errorMessage, throwable);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(errorMessage);
        return errorDto;
    }

    private DataIntegrityViolationException replaceErrMsgForConstraintViolationException(
            DataIntegrityViolationException dive) {
        val thr = dive.getCause();
        if (thr instanceof org.hibernate.exception.ConstraintViolationException cve) {
            val constraint =
                    RuleOperationsConstraints.value(cve.getConstraintName());
            return new DataIntegrityViolationException(
                    constraint != RuleOperationsConstraints.UNKNOWN_CONSTRAINT ?
                            constraint.errMsg :
                            String.format(constraint.errMsg, cve.getConstraintName(), cve.getMessage()));
        }
        return dive;
    }

    private static String getReason(final Throwable throwable) {
        return Optional.of(throwable)
                .map(Throwable::getCause)
                .map(Throwable::getCause)
                .map(Throwable::getMessage)
                .orElseGet(throwable::getMessage);
    }

    private enum RuleOperationsConstraints {
        UNKNOWN_CONSTRAINT(
                "unknown constraint",
                "Unknown constraint violation exception. Constraint name: [%s]. Msg: [%s]"),
        CLIENT_NAME_CONSTRAINT(
                "uc_bank_client",
                "Client with such name and surname has already existed"),
        ACCOUNT_NUMBER_CONSTRAINT(
                "uc_account_number",
                "Account with such number has already existed");

        private final String name;
        private final String errMsg;

        RuleOperationsConstraints(String name, String errMsg) {
            this.name = name;
            this.errMsg = errMsg;
        }

        static RuleOperationsConstraints value(String str) {
            return Arrays.stream(RuleOperationsConstraints.values())
                    .filter(roc -> roc.name.equals(str))
                    .findFirst()
                    .orElse(UNKNOWN_CONSTRAINT);
        }
    }

    @Data
    public static class ErrorDto {
        private String message;
    }
}
