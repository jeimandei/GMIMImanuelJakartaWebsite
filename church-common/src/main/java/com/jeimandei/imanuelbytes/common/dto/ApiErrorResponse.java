package com.jeimandei.imanuelbytes.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<String> violations;

    public ApiErrorResponse(int status, String error, String message, String path, List<String> violations) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.violations = violations;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<String> getViolations() {
        return violations;
    }
}
