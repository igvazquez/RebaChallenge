package com.example.demo.models.exceptions;

public class IllegalAgeException extends RuntimeException{
    public IllegalAgeException(final String message) {
        super(message);
    }
}
