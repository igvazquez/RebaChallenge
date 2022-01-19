package com.example.demo;

import com.example.demo.models.*;

import java.time.LocalDateTime;

public class TestData {

    public static Person getFakePerson(){
        return new Person()
                .id(1L)
                .name("Ignacio")
                .birthdate(LocalDateTime.of(1998,19,12,12,12).toString())
                .document(getFakeDocument())
                .phone(getFakePhone())
                .address(getFakeAddress());
    }

    public static Document getFakeDocument(){
        return new Document()
                .id(1L)
                .type("DNI")
                .document("21123123")
                .residenceCountry("Argentina");
    }

    public static Phone getFakePhone(){
        return new Phone()
                .id(1L)
                .number("1112345678");
    }

    public static Address getFakeAddress(){
        return new Address()
                .id(1L)
                .street("Fake street")
                .number("11A")
                .appartment(false);
    }

    public static PersonEntity getFakePersonEntity(){
        return PersonEntity.builder()
                .id(1L)
                .name("Ignacio")
                .birthdate(LocalDateTime.of(1998,12,19,12,12))
                .document(getFakeDocumentEntity())
                .address(getFakeAddressEntity())
                .phone(getFakePhoneEntity())
                .build();
    }

    public static DocumentEntity getFakeDocumentEntity(){
        return DocumentEntity.builder()
                .id(1L)
                .type("DNI")
                .document("21123123")
                .residenceCountry("Argentina")
                .build();
    }

    public static AddressEntity getFakeAddressEntity(){
        return AddressEntity.builder()
                .id(1L)
                .street("Fake street")
                .number("11A")
                .apartment(false)
                .build();
    }

    public static PhoneEntity getFakePhoneEntity(){
        return PhoneEntity.builder()
                .id(1L)
                .number("1112345678")
                .build();
    }
}
