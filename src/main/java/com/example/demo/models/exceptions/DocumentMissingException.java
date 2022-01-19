package com.example.demo.models.exceptions;

public class DocumentMissingException extends RuntimeException{
    public DocumentMissingException() {
        super("A document must be provided to create a person");
    }
}
