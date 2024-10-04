package com.great.fpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class InvoicePaymentRequest {

    @JsonProperty("numero_referencia_comprobante")
    private String reference;

    @JsonProperty("codigo_servicio")
    private int code;

    @JsonProperty("monto_deuda_total")
    private BigDecimal totalDebt;

    @JsonProperty("monto_abonado")
    private BigDecimal amount;

}
