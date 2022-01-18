package com.example.demo.models.exceptions;

public class PersonNotExistsException extends RuntimeException{
    public PersonNotExistsException(final Long userId) {
        super("Person with id "+ userId + " doesn't exists");
    }
}
