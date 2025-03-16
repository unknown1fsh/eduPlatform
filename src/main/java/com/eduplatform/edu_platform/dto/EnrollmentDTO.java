package com.eduplatform.edu_platform.dto;

import com.eduplatform.edu_platform.enums.EnrollmentStatus;

public class EnrollmentDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private String enrollmentDate;
    private EnrollmentStatus status;

    public EnrollmentDTO() {}

    public EnrollmentDTO(Long id, Long userId, Long courseId, String enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}