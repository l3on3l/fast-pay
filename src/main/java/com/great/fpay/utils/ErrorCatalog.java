package com.great.fpay.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

    USER_NOT_FOUND("ERR_USER_001", "User not found."),

    SERVICE_PROVIDER_NOT_FOUND("ERR_SERVICE_PROVIDER_001", "Service provider not found."),

    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
