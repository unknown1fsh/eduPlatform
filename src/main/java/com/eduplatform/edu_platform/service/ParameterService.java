package com.eduplatform.edu_platform.service;

public interface ParameterService {
    String getParameterValue(String paramKey);
    void saveParameter(String paramKey, String paramValue, String description);
}