package com.great.fpay.service.exception;

import com.great.fpay.dto.ErrorResponse;
import com.great.fpay.utils.ErrorCatalog;
import org.springframework.http.HttpStatus;

public interface IExceptionHandlingService {

    ErrorResponse handleHttpRequestException(HttpStatus status, ErrorCatalog errorCase);

}
