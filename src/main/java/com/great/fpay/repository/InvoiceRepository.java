package com.great.fpay.repository;

import com.great.fpay.entity.Invoice;
import com.great.fpay.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUserIdAndStatus(Long userId, InvoiceStatus status);

    Optional<Invoice> findByServiceProviderIdAndReference(Long serviceProviderId, String reference);

}
