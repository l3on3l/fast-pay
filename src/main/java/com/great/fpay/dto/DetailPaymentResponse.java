package com.great.fpay.dto;

import com.great.fpay.entity.Invoice;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class DetailPaymentResponse {

    private Invoice invoice;

    private LocalDate paymentDate;

    private BigDecimal totalAmount;

    private BigDecimal paidAmount;

}
