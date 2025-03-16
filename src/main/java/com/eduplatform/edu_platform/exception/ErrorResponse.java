package com.eduplatform.edu_platform.exception;

public class ErrorResponse {
    private String message;
    private String errorCode;
    private String timestamp;

    // Üç parametreli constructor
    public ErrorResponse(String message, String errorCode, String timestamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

    // Varsayılan constructor (opsiyonel)
    public ErrorResponse() {}

    // Getter ve Setter metodları
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}