package com.great.fpay.repository;

import com.great.fpay.entity.Invoice;
import com.great.fpay.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUserIdAndStatus(Long userId, InvoiceStatus status);

    Optional<Invoice> findByServiceProviderIdAndReferenceAndCreatedAt(Long serviceProviderId, String reference, LocalDate createdAt);
}
