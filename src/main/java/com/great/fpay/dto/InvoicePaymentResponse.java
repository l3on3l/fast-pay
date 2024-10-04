package com.great.fpay.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class InvoicePaymentResponse {

    private UserResponse userResponse;

    private ServiceProviderResponse serviceProvider;

    private BigDecimal totalDebt;

    private BigDecimal amountPaid;

    private LocalDate datePaid;

    private String message;
}
