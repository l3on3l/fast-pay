package com.great.fpay.exceptions;

import com.great.fpay.utils.ErrorCatalog;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

    private final ErrorCatalog errorCatalog;

    private final HttpStatus status;

    public UserException(HttpStatus status, ErrorCatalog errorCatalog) {
        super(errorCatalog.getMessage());
        this.status = status;
        this.errorCatalog = errorCatalog;
    }

}
