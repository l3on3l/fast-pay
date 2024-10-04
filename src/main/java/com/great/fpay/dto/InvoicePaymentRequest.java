package com.great.fpay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class InvoicePaymentRequest {

    @NotEmpty(message = "The field numero_referencia_comprobante cannot be empty or null.")
    @JsonProperty("numero_referencia_comprobante")
    private String reference;

    @NotNull(message = "The field codigo_servicio cannot be empty or null.")
    @JsonProperty("codigo_servicio")
    private int code;

    @NotNull(message = "The field monto_deuda_total cannot be empty or null.")
    @JsonProperty("monto_deuda_total")
    private BigDecimal totalDebt;

    @NotNull(message = "The field monto_abonado cannot be empty or null.")
    @JsonProperty("monto_abonado")
    private BigDecimal amount;

}
