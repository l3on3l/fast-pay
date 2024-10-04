package com.great.fpay.mapper;

import com.great.fpay.dto.DetailPaymentResponse;
import com.great.fpay.entity.Invoice;
import com.great.fpay.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = Invoice.class)
public interface PaymentMapper {

    DetailPaymentResponse toDetailPaymentResponse(Payment payment);

}
