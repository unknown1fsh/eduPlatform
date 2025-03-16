package com.eduplatform.edu_platform.entity;

import com.eduplatform.edu_platform.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "parameters")
public class Parameter extends BaseEntity {
    @Column(name = "param_key", nullable = false, unique = true)
    private String paramKey;

    @Column(name = "param_value", nullable = false)
    private String paramValue;

    @Column
    private String description;

    // Varsayılan Constructor
    public Parameter() {
        super();
    }

    // Parametreli Constructor
    public Parameter(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String paramKey, 
                     String paramValue, String description) {
        super(id, createdAt, updatedAt);
        this.paramKey = paramKey;
        this.paramValue = paramValue;
        this.description = description;
    }

    // Getter ve Setter'lar
    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}