package com.example.demo.models.exceptions;

public class PersonNotExistsException extends RuntimeException{
    public PersonNotExistsException(final String message) {
        super(message);
    }
}
