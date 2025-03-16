package com.eduplatform.edu_platform.service;

import com.eduplatform.edu_platform.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO enrollUser(Long userId, Long courseId);
    EnrollmentDTO approveEnrollment(Long enrollmentId);
    EnrollmentDTO cancelEnrollment(Long enrollmentId);
    List<EnrollmentDTO> getEnrollmentsByUser(Long userId);
}