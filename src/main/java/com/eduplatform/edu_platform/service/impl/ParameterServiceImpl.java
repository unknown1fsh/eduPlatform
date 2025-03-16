package com.eduplatform.edu_platform.service.impl;

import com.eduplatform.edu_platform.entity.Parameter;
import com.eduplatform.edu_platform.exception.CustomException;
import com.eduplatform.edu_platform.repository.ParameterRepository;
import com.eduplatform.edu_platform.service.ParameterService;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl implements ParameterService {
    private final ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public String getParameterValue(String paramKey) {
        return parameterRepository.findByParamKey(paramKey)
                .map(Parameter::getParamValue)
                .orElseThrow(() -> new CustomException("Parameter not found: " + paramKey, "PARAM_NOT_FOUND"));
    }

    @Override
    public void saveParameter(String paramKey, String paramValue, String description) {
        if (parameterRepository.findByParamKey(paramKey).isPresent()) {
            throw new CustomException("Parameter already exists: " + paramKey, "PARAM_EXISTS");
        }
        Parameter parameter = new Parameter();
        parameter.setParamKey(paramKey);
        parameter.setParamValue(paramValue);
        parameter.setDescription(description);
        parameterRepository.save(parameter);
    }
}