package com.eduplatform.edu_platform.entity;

import com.eduplatform.edu_platform.entity.base.BaseEntity;
import com.eduplatform.edu_platform.enums.CourseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private CourseStatus status;

    // Varsayılan Constructor
    public Course() {
        super();
    }

    // Parametreli Constructor
    public Course(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description, 
                  User instructor, BigDecimal price, CourseStatus status) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.status = status;
    }

    // Getter ve Setter'lar
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}