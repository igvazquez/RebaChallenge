package com.example.demo.models.exceptions;

import lombok.Value;
import org.springframework.http.HttpStatus;

import java.util.List;

@Value
public class ApiError {

    HttpStatus status;
    String message;
    List<String> errors;

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = List.of(error);
    }
}
