package com.eduplatform.edu_platform.service.impl;

import com.eduplatform.edu_platform.dto.EnrollmentDTO;
import com.eduplatform.edu_platform.entity.Course;
import com.eduplatform.edu_platform.entity.Enrollment;
import com.eduplatform.edu_platform.entity.User;
import com.eduplatform.edu_platform.enums.EnrollmentStatus;
import com.eduplatform.edu_platform.exception.CustomException;
import com.eduplatform.edu_platform.mapper.EnrollmentMapper;
import com.eduplatform.edu_platform.repository.CourseRepository;
import com.eduplatform.edu_platform.repository.EnrollmentRepository;
import com.eduplatform.edu_platform.repository.UserRepository;
import com.eduplatform.edu_platform.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, 
                                 UserRepository userRepository, 
                                 CourseRepository courseRepository, 
                                 EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    public EnrollmentDTO enrollUser(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CustomException("Course not found", "COURSE_NOT_FOUND"));

        if (enrollmentRepository.findByUserIdAndCourseId(userId, courseId).isPresent()) {
            throw new CustomException("User already enrolled in this course", "ALREADY_ENROLLED");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(java.time.LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.PENDING);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(savedEnrollment);
    }

    @Override
    public EnrollmentDTO approveEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new CustomException("Enrollment not found", "ENROLLMENT_NOT_FOUND"));
        enrollment.setStatus(EnrollmentStatus.APPROVED);
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(updatedEnrollment);
    }

    @Override
    public EnrollmentDTO cancelEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new CustomException("Enrollment not found", "ENROLLMENT_NOT_FOUND"));
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(updatedEnrollment);
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByUser(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);
        return enrollmentMapper.toDTOList(enrollments);
    }
}