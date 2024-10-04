package com.great.fpay.controller;

import com.great.fpay.dto.FullInvoiceResponse;
import com.great.fpay.dto.InvoicePaymentRequest;
import com.great.fpay.dto.InvoicePaymentResponse;
import com.great.fpay.service.invoice.IInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @GetMapping("/user/{userId}/pending")
    public List<FullInvoiceResponse> getPendingInvoicesByUserId(@PathVariable Long userId) {
        return invoiceService.getPendingInvoicesByUserId(userId);
    }

    @PostMapping("/user/{userId}/makePayment")
    public InvoicePaymentResponse makePayment(
            @PathVariable Long userId,
            @Valid @RequestBody InvoicePaymentRequest request
    ) {
        return invoiceService.makePayment(userId, request);
    }

}
