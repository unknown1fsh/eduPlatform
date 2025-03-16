package com.eduplatform.edu_platform.repository;

import com.eduplatform.edu_platform.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Optional<Parameter> findByParamKey(String paramKey);
}