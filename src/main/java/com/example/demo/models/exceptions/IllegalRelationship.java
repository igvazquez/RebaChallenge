package com.example.demo.models.exceptions;

public class IllegalRelationship extends RuntimeException{

    public IllegalRelationship(final String message) {
        super(message);
    }
}
