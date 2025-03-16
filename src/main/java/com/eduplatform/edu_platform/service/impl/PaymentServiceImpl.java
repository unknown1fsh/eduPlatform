package com.eduplatform.edu_platform.service.impl;

import com.eduplatform.edu_platform.dto.PaymentDTO;
import com.eduplatform.edu_platform.entity.Enrollment;
import com.eduplatform.edu_platform.entity.Payment;
import com.eduplatform.edu_platform.enums.PaymentStatus;
import com.eduplatform.edu_platform.exception.CustomException;
import com.eduplatform.edu_platform.mapper.PaymentMapper;
import com.eduplatform.edu_platform.repository.EnrollmentRepository;
import com.eduplatform.edu_platform.repository.PaymentRepository;
import com.eduplatform.edu_platform.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PaymentMapper paymentMapper;
    private final RestTemplate restTemplate;

    public PaymentServiceImpl(PaymentRepository paymentRepository, 
                              EnrollmentRepository enrollmentRepository, 
                              PaymentMapper paymentMapper, 
                              RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.paymentMapper = paymentMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentDTO processPayment(Long enrollmentId, Double amount) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new CustomException("Enrollment not found", "ENROLLMENT_NOT_FOUND"));

        // Örnek dış ödeme servisi çağrısı
        String paymentApiUrl = "https://external-payment-api.com/process";
        String transactionId = restTemplate.postForObject(paymentApiUrl, amount, String.class);

        Payment payment = new Payment();
        payment.setEnrollment(enrollment);
        payment.setAmount(BigDecimal.valueOf(amount));
        payment.setPaymentDate(java.time.LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setTransactionId(transactionId);

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.toDTO(savedPayment);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Payment not found", "PAYMENT_NOT_FOUND"));
        return paymentMapper.toDTO(payment);
    }
}