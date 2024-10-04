package com.great.fpay.controller;

import com.great.fpay.dto.ErrorResponse;
import com.great.fpay.exceptions.InvoiceException;
import com.great.fpay.exceptions.PaymentException;
import com.great.fpay.exceptions.ServiceProviderException;
import com.great.fpay.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.great.fpay.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ErrorResponse handleUserException(UserException ex) {
        return ErrorResponse.builder()
                .status(ex.getStatus().value())
                .code(ex.getErrorCatalog().getCode())
                .message(ex.getErrorCatalog().getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ServiceProviderException.class)
    public ErrorResponse handleServiceException(ServiceProviderException ex) {
        return ErrorResponse.builder()
                .status(ex.getStatus().value())
                .code(ex.getErrorCatalog().getCode())
                .message(ex.getErrorCatalog().getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(InvoiceException.class)
    public ErrorResponse handleInvoiceException(InvoiceException ex) {
        return ErrorResponse.builder()
                .status(ex.getStatus().value())
                .code(ex.getErrorCatalog().getCode())
                .message(ex.getErrorCatalog().getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(PaymentException.class)
    public ErrorResponse handlePaymentException(InvoiceException ex) {
        return ErrorResponse.builder()
                .status(ex.getStatus().value())
                .code(ex.getErrorCatalog().getCode())
                .message(ex.getErrorCatalog().getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServeError(Exception ex) {
        return ErrorResponse.builder()
                .code(GENERIC_ERROR.getCode())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(GENERIC_ERROR.getMessage())
                .detailMessages(Collections.singletonList(ex.getMessage()))
                .timeStamp(LocalDateTime.now())
                .build();
    }

}
