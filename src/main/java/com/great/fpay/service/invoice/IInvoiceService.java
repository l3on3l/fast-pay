package com.great.fpay.service.invoice;

import com.great.fpay.dto.FullInvoiceResponse;
import com.great.fpay.dto.InvoicePaymentRequest;
import com.great.fpay.dto.InvoicePaymentResponse;

import java.math.BigDecimal;
import java.util.List;

public interface IInvoiceService {

    List<FullInvoiceResponse> getPendingInvoicesByUserId(Long userId);

    InvoicePaymentResponse makePayment(Long userId, InvoicePaymentRequest request);

}
