package com.eduplatform.edu_platform.mapper;

import com.eduplatform.edu_platform.dto.CourseDTO;
import com.eduplatform.edu_platform.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "instructorId", source = "instructor.id")
    @Mapping(target = "price", source = "price")
    CourseDTO toDTO(Course course);

    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "price", source = "price")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    List<CourseDTO> toDTOList(List<Course> courses);
}