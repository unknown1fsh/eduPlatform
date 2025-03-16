package com.eduplatform.edu_platform.mapper;

import com.eduplatform.edu_platform.dto.PaymentDTO;
import com.eduplatform.edu_platform.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "enrollmentId", source = "enrollment.id")
    @Mapping(target = "paymentDate", source = "paymentDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    PaymentDTO toDTO(Payment payment);

    @Mapping(target = "enrollment", ignore = true) // Enrollment entity'si manuel set edilecek
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Payment toEntity(PaymentDTO paymentDTO);

    List<PaymentDTO> toDTOList(List<Payment> payments);
}