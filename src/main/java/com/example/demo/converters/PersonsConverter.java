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
                .document(DocumentConverter.convertToDocumentDto(person.getDocument()));
    }

//    public static Person convertToFullPersonDto(final PersonEntity person){
//        var p = new Person()
//                .id(person.getId())
//                .name(person.getName())
//                .birthdate(person.getBirthdate().toString())
//                .document(DocumentConverter.convertToDocumentDto(person.getDocument()));
//
//        if (person.getParent() != null){
//            p.setParent(convertToPersonDto(person.getParent()));
//        }
//        if (person.getChild() != null){
//            p.setChild(convertToPersonDto(person.getChild()));
//        }
//
//        return p;
//    }

    public static PersonEntity convertToEntity(final Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .birthdate(LocalDateTime.parse(person.getBirthdate()))
                .document(DocumentConverter.convertToDocumentEntity(person.getDocument()))
                .build();
    }
}
