package com.eduplatform.edu_platform.service;

import com.eduplatform.edu_platform.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    CourseDTO getCourseById(Long id);
    List<CourseDTO> getAllCourses();
    List<CourseDTO> findCoursesByTitle(String title); // Specification ile dinamik sorgu
    void deleteCourse(Long id);
    void publishCourse(Long id);
}