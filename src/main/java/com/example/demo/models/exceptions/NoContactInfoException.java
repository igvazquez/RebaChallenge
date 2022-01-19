package com.example.demo.models.exceptions;

public class NoContactInfoException extends RuntimeException{
    public NoContactInfoException() {
        super("User must provide at least one valid contact info");
    }
}
