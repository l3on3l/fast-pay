package com.great.fpay.controller;

import com.great.fpay.dto.DetailPaymentResponse;
import com.great.fpay.service.payment.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final IPaymentService paymentService;

    @GetMapping("/date-range")
    public List<DetailPaymentResponse> getPaymentsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return paymentService.findPaymentsByDateRange(start, end);
    }

    @GetMapping("/service")
    public List<DetailPaymentResponse> getPaymentsByServiceCode(@RequestParam int code) {
        return paymentService.findByServiceCode(code);
    }
}
