package com.example.demo.converters;


import com.example.demo.models.Person;
import com.example.demo.models.PersonEntity;

import java.time.LocalDateTime;

public final class PersonsConverter {

    private PersonsConverter() {
        // Utility class
    }

    public static Person convertToPersonDto(final PersonEntity person){
        var p = new Person()
                .id(person.getId())
                .name(person.getName())
                .birthdate(person.getBirthdate().toString())
                .document(DocumentConverter.convertToDocumentDto(person.getDocument()));

        if(person.getAddress() != null){
            p.address(AddressConverter.convertToAddressDto(person.getAddress()));
        }
        if (person.getPhone() != null){
            p.phone(PhoneConverter.convertToPhoneDto(person.getPhone()));
        }

        return p;
    }

    public static PersonEntity convertToEntity(final Person person) {
        var p = PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .birthdate(LocalDateTime.parse(person.getBirthdate()))
                .build();

        if(person.getDocument() != null){
            p.setDocument(DocumentConverter.convertToDocumentEntity(person.getDocument(), p));
        }

        if (person.getPhone() != null){
            p.setPhone(PhoneConverter.convertToPhoneEntity(person.getPhone(), p));
        }
        if (person.getAddress() != null){
            p.setAddress(AddressConverter.convertToAddressEntity(person.getAddress(), p));
        }

        return p;
    }
}
