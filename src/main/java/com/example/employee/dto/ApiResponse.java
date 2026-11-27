
package com.example.employee.dto;

import java.time.LocalDateTime;

public class ApiResponse {

    private LocalDateTime timestamp;
    private String message;
    private Object data;
    private int status;

    public ApiResponse(String message, Object data, int status) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
