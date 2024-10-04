package com.great.fpay.service.payment;

import com.great.fpay.controller.DetailPaymentResponse;
import com.great.fpay.exceptions.PaymentException;
import com.great.fpay.exceptions.UserException;
import com.great.fpay.mapper.PaymentMapper;
import com.great.fpay.repository.PaymentRepository;
import com.great.fpay.utils.ErrorCatalog;
import com.great.fpay.utils.ServiceProviderCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    @Override
    public List<DetailPaymentResponse> findPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate)
                .stream()
                .map(paymentMapper::toDetailPaymentResponse)
                .toList();
    }

    @Override
    public List<DetailPaymentResponse> findByServiceCode(int serviceCode) {
        var code = ServiceProviderCodeType.getFormattedCode(serviceCode);
        var paymentList = paymentRepository.findByServiceCode(code)
                .stream()
                .map(paymentMapper::toDetailPaymentResponse)
                .toList();

        if (paymentList.isEmpty()) {
            throw new UserException(HttpStatus.NOT_FOUND, ErrorCatalog.PAYMENT_NOT_FOUND);
        }

        return paymentList;
    }

}
