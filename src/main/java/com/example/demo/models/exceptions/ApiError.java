package com.example.demo.models.exceptions;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ApiError {

    HttpStatus status;
    String message;

    public ApiError(final HttpStatus status, final String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
