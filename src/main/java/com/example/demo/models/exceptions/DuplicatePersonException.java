package com.example.demo.models.exceptions;

import com.example.demo.models.PersonEntity;

public class DuplicatePersonException extends RuntimeException{
    public DuplicatePersonException(final PersonEntity person) {
        super(String.format("Duplicate user with Residence Country: %s; Document Type: %s; Document: %s",
                person.getDocument().getResidenceCountry(),
                person.getDocument().getType(),
                person.getDocument().getDocument()));
    }
}
