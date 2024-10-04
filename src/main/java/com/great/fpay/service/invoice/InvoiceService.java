package com.great.fpay.service.invoice;

import com.great.fpay.dto.FullInvoiceResponse;
import com.great.fpay.dto.InvoicePaymentRequest;
import com.great.fpay.dto.InvoicePaymentResponse;
import com.great.fpay.entity.*;
import com.great.fpay.exceptions.InvoiceException;
import com.great.fpay.exceptions.UserException;
import com.great.fpay.mapper.InvoiceMapper;
import com.great.fpay.mapper.ServiceProviderMapper;
import com.great.fpay.mapper.UserMapper;
import com.great.fpay.repository.InvoiceRepository;
import com.great.fpay.repository.PaymentRepository;
import com.great.fpay.repository.ServiceProviderRepository;
import com.great.fpay.repository.UserRepository;
import com.great.fpay.utils.ErrorCatalog;
import com.great.fpay.utils.ServiceProviderCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {

    final InvoiceRepository invoiceRepository;

    final UserRepository userRepository;

    final PaymentRepository paymentRepository;

    final ServiceProviderRepository serviceProviderRepository;

    final InvoiceMapper invoiceMapper;

    final UserMapper userMapper;

    final ServiceProviderMapper serviceProviderMapper;

    @Override
    public List<FullInvoiceResponse> getPendingInvoicesByUserId(Long userId) {
        return invoiceRepository.findByUserIdAndStatus(userId, InvoiceStatus.PENDING)
                .stream()
                .map(invoiceMapper::toFullInvoiceResponse)
                .toList();
    }

    @Override
    @Transactional
    public InvoicePaymentResponse makePayment(Long userId, InvoicePaymentRequest request) {
        // Obtener el usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(HttpStatus.NOT_FOUND, ErrorCatalog.USER_NOT_FOUND));

        // Obtener la factura
        var serviceCode = ServiceProviderCodeType.getFormattedCode(request.getCode());
        ServiceProvider serviceProvider = serviceProviderRepository.findByServiceCode(serviceCode);
        Invoice invoice = invoiceRepository.findByServiceProviderIdAndReferenceAndCreatedAt(serviceProvider.getId(), request.getReference(), request.getDate())
                .orElseThrow(() -> new InvoiceException(HttpStatus.NOT_FOUND, ErrorCatalog.INVOICE_NOT_FOUND));

        // Verificar que la factura pertenece al usuario
        if (!invoice.getUser().getId().equals(userId) && !request.getTotalDebt().equals(invoice.getAmount())) {
            throw  new RuntimeException("The invoice doesn't belong to the user");
        }

        // Verificar que la factura está pendiente
        if (!invoice.getStatus().equals(InvoiceStatus.PENDING)) {
            throw  new RuntimeException("The invoice is already paid");
        }

        // Verificar que el usuario tiene suficiente saldo
        if (user.getBalance().compareTo(request.getAmount()) < 0) {
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.INSUFFICIENT_BALANCE);
        }

        // Verificar que el pago no excede la deuda
        if (invoice.getRemainingAmount().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("The payment amount exceeds the debt");
        }

        // Realizar el pago
        user.setBalance(user.getBalance().subtract(request.getAmount()));
        invoice.setRemainingAmount(invoice.getRemainingAmount().subtract(request.getAmount()));

        // Si la deuda se paga completamente, actualizar el estado de la factura
        if (invoice.getRemainingAmount().compareTo(BigDecimal.ZERO) == 0) {
            invoice.setStatus(InvoiceStatus.COMPLETED);
        }

        // Guardar los cambios
        userRepository.save(user);
        invoiceRepository.save(invoice);

        // Registrar el pago
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setPaidAmount(request.getAmount());
        payment.setTotalAmount(invoice.getAmount());
        paymentRepository.save(payment);

        // Pago realizado
        return InvoicePaymentResponse.builder()
                .userResponse(userMapper.toUserResponse(user))
                .serviceProvider(serviceProviderMapper.toServiceProviderResponse(serviceProvider))
                .totalDebt(request.getTotalDebt())
                .amountPaid(request.getAmount())
                .datePaid(payment.getPaymentDate())
                .message("Success Payment")
                .build();
    }
}
