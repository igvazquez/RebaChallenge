package com.example.demo.converters;


import com.example.demo.models.Person;
import com.example.demo.models.PersonEntity;

public final class PersonsConverter {

    private PersonsConverter() {
        // utility class
    }

    public static Person convertToPersonDto(final PersonEntity person){
        return new Person()
                .id(person.getId())
                .name(person.getName())
                .birthdate(person.getBirthdate().toString());
    }
}
