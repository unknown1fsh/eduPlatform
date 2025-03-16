package com.eduplatform.edu_platform.service.impl;

import com.eduplatform.edu_platform.dto.CourseDTO;
import com.eduplatform.edu_platform.entity.Course;
import com.eduplatform.edu_platform.entity.User;
import com.eduplatform.edu_platform.enums.CourseStatus;
import com.eduplatform.edu_platform.exception.CustomException;
import com.eduplatform.edu_platform.mapper.CourseMapper;
import com.eduplatform.edu_platform.repository.CourseRepository;
import com.eduplatform.edu_platform.repository.UserRepository;
import com.eduplatform.edu_platform.service.CourseService;
import com.eduplatform.edu_platform.specification.CourseSpecification;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        User instructor = userRepository.findById(courseDTO.getInstructorId())
                .orElseThrow(() -> new CustomException("Instructor not found", "INSTRUCTOR_NOT_FOUND"));
        Course course = courseMapper.toEntity(courseDTO);
        course.setInstructor(instructor);
        course.setStatus(CourseStatus.DRAFT);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Course not found", "COURSE_NOT_FOUND"));
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(BigDecimal.valueOf(courseDTO.getPrice()));
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toDTO(updatedCourse);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Course not found", "COURSE_NOT_FOUND"));
        return courseMapper.toDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDTOList(courses);
    }

    @Override
    public List<CourseDTO> findCoursesByTitle(String title) {
        List<Course> courses = courseRepository.findAll(CourseSpecification.filterCoursesByTitle(title));
        return courseMapper.toDTOList(courses);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Course not found", "COURSE_NOT_FOUND"));
        courseRepository.delete(course);
    }

    @Override
    public void publishCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Course not found", "COURSE_NOT_FOUND"));
        course.setStatus(CourseStatus.PUBLISHED);
        courseRepository.save(course);
    }
}