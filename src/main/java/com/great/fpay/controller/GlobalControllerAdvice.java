package com.great.fpay.controller;

import com.great.fpay.dto.ErrorResponse;
import com.great.fpay.exceptions.ServiceProviderNotFoundException;
import com.great.fpay.service.exception.IExceptionHandlingService;
import com.great.fpay.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.great.fpay.utils.ErrorCatalog.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final IExceptionHandlingService exceptionHandlingService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException() {
        return exceptionHandlingService.handleHttpRequestException(HttpStatus.NOT_FOUND, USER_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServiceProviderNotFoundException.class)
    public ErrorResponse handleServiceProviderNotFoundException() {
        return exceptionHandlingService.handleHttpRequestException(HttpStatus.NOT_FOUND, SERVICE_PROVIDER_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServeError(Exception exception) {
        return exceptionHandlingService.handleHttpRequestException(HttpStatus.INTERNAL_SERVER_ERROR, GENERIC_ERROR);
    }
}
