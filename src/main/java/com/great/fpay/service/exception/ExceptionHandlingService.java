package com.great.fpay.service.exception;

import com.great.fpay.dto.ErrorResponse;
import com.great.fpay.utils.ErrorCatalog;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExceptionHandlingService implements IExceptionHandlingService {

    @Override
    public ErrorResponse handleHttpRequestException(HttpStatus status, ErrorCatalog errorCase) {
        return ErrorResponse.builder()
                .status(status.value())
                .code(errorCase.getCode())
                .message(errorCase.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }

}
