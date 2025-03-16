package com.eduplatform.edu_platform.specification;

import com.eduplatform.edu_platform.entity.Course;
import com.eduplatform.edu_platform.enums.CourseStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {

    // Başlığa göre filtreleme
    public static Specification<Course> filterCoursesByTitle(String title) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    // Daha geniş filtreleme (başlık, durum ve eğitmen ID'sine göre)
    public static Specification<Course> filterCourses(String title, CourseStatus status, Long instructorId) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (instructorId != null) {
                predicates.add(cb.equal(root.get("instructor").get("id"), instructorId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}