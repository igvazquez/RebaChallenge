package com.example.demo.converters;


import com.example.demo.models.Person;
import com.example.demo.models.PersonEntity;

import java.time.LocalDateTime;

public final class PersonsConverter {

    private PersonsConverter() {
        // Utility class
    }

    public static Person convertToPersonDto(final PersonEntity person){
        return new Person()
                .id(person.getId())
                .name(person.getName())
                .birthdate(person.getBirthdate().toString())
                .residenceCountry(person.getResidenceCountry())
                .document(DocumentConverter.convertToDocumentDto(person.getDocument()));
    }

    public static PersonEntity convertToEntity(final Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .birthdate(LocalDateTime.parse(person.getBirthdate()))
                .residenceCountry(person.getResidenceCountry())
                .document(DocumentConverter.convertToDocumentEntity(person.getDocument()))
                .build();
    }
}
