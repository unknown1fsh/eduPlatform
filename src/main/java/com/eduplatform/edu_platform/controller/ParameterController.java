package com.eduplatform.edu_platform.controller;

import com.eduplatform.edu_platform.service.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostMapping
    public ResponseEntity<Void> saveParameter(
            @RequestParam String paramKey, 
            @RequestParam String paramValue, 
            @RequestParam(required = false) String description) {
        parameterService.saveParameter(paramKey, paramValue, description);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{paramKey}")
    public ResponseEntity<String> getParameterValue(@PathVariable String paramKey) {
        String value = parameterService.getParameterValue(paramKey);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}