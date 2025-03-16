package com.eduplatform.edu_platform.service;

import com.eduplatform.edu_platform.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO processPayment(Long enrollmentId, Double amount);
    PaymentDTO getPaymentById(Long id);
}