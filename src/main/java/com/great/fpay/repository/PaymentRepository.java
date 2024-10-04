package com.great.fpay.repository;

import com.great.fpay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Payment p JOIN p.invoice i JOIN i.serviceProvider sp WHERE sp.serviceCode = :serviceCode")
    List<Payment> findByServiceCode(String serviceCode);
}
