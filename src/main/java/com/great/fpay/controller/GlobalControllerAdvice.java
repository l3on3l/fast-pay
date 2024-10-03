package com.great.fpay.controller;

import com.great.fpay.dto.ErrorResponse;
import com.great.fpay.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static com.great.fpay.utils.ErrorCatalog.GENERIC_ERROR;
import static com.great.fpay.utils.ErrorCatalog.USER_NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException() {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .code(USER_NOT_FOUND.getCode())
                .message(USER_NOT_FOUND.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServeError(Exception exception) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
