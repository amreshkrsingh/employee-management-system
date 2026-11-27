
package com.example.employee.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private String message;
    private T data;
    private int status;

    public ApiResponse(String message, T data, int status) {
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

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
