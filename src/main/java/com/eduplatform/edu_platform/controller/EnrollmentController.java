package com.eduplatform.edu_platform.controller;

import com.eduplatform.edu_platform.dto.EnrollmentDTO;
import com.eduplatform.edu_platform.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enrollUser(
            @RequestParam Long userId, 
            @RequestParam Long courseId) {
        EnrollmentDTO enrollment = enrollmentService.enrollUser(userId, courseId);
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<EnrollmentDTO> approveEnrollment(@PathVariable Long id) {
        EnrollmentDTO enrollment = enrollmentService.approveEnrollment(id);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<EnrollmentDTO> cancelEnrollment(@PathVariable Long id) {
        EnrollmentDTO enrollment = enrollmentService.cancelEnrollment(id);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByUser(@PathVariable Long userId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByUser(userId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }
}