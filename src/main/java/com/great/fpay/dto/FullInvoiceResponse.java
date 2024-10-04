package com.great.fpay.dto;

import com.great.fpay.entity.InvoiceStatus;
import com.great.fpay.entity.PaymentOption;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class FullInvoiceResponse {

    private UserResponse user;

    private BigDecimal amount;

    private LocalDate dueDate;

    private BigDecimal remainingAmount;

    private String reference;

    private InvoiceStatus status;

    private PaymentOption paymentOption;

}
