package com.great.fpay.utils;

import lombok.Getter;

@Getter
public enum ServiceProviderCodeType {

    ANDE("code-1"),
    TIGO("code-2"),
    PERSONAL_FLOW_HOGAR("code-3");

    private final String serviceCode;

    ServiceProviderCodeType(String code) {
        this.serviceCode = code;
    }

    public static String getFormattedCode(int code) {
        return "code-" + String.format("%d", code);
    }
}
