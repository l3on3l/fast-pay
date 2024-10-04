package com.great.fpay.mapper;

import com.great.fpay.dto.FullInvoiceResponse;
import com.great.fpay.entity.Invoice;
import com.great.fpay.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = User.class)
public interface InvoiceMapper {

    FullInvoiceResponse toFullInvoiceResponse(Invoice invoice);

}