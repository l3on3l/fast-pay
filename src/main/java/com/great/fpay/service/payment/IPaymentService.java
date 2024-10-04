package com.great.fpay.service.payment;

import com.great.fpay.controller.DetailPaymentResponse;

import java.time.LocalDate;
import java.util.List;

public interface IPaymentService {

    List<DetailPaymentResponse> findPaymentsByDateRange(LocalDate startDate, LocalDate endDate);

    List<DetailPaymentResponse> findByServiceCode(int serviceCode);

}
