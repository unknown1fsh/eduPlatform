package com.eduplatform.edu_platform.entity;

import com.eduplatform.edu_platform.entity.base.BaseEntity;
import com.eduplatform.edu_platform.enums.EnrollmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
public class Enrollment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate;

    @Column(nullable = false)
    private EnrollmentStatus status;

    // Varsayılan Constructor
    public Enrollment() {
        super();
    }

    // Parametreli Constructor
    public Enrollment(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User user, Course course, 
                      LocalDateTime enrollmentDate, EnrollmentStatus status) {
        super(id, createdAt, updatedAt);
        this.user = user;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getter ve Setter'lar
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}