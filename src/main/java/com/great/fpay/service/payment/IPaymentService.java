package com.great.fpay.service.payment;

import com.great.fpay.dto.DetailPaymentResponse;

import java.time.LocalDate;
import java.util.List;

public interface IPaymentService {

    List<DetailPaymentResponse> findPaymentsByDateRange(LocalDate startDate, LocalDate endDate);

    List<DetailPaymentResponse> findByServiceCode(int serviceCode);

}
