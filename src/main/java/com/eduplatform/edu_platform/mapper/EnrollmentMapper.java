package com.eduplatform.edu_platform.mapper;

import com.eduplatform.edu_platform.dto.EnrollmentDTO;
import com.eduplatform.edu_platform.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "enrollmentDate", source = "enrollmentDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EnrollmentDTO toDTO(Enrollment enrollment);

    @Mapping(target = "user", ignore = true) // User entity'si manuel set edilecek
    @Mapping(target = "course", ignore = true) // Course entity'si manuel set edilecek
    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Enrollment toEntity(EnrollmentDTO enrollmentDTO);

    List<EnrollmentDTO> toDTOList(List<Enrollment> enrollments);
}