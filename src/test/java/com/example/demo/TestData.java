package com.example.demo;

import com.example.demo.models.Document;
import com.example.demo.models.DocumentEntity;
import com.example.demo.models.Person;
import com.example.demo.models.PersonEntity;

import java.time.LocalDateTime;

public class TestData {

    public static Person getFakePerson(){
        return new Person()
                .id(1L)
                .name("Ignacio")
                .birthdate(LocalDateTime.of(1998,19,12,12,12).toString())
                .document(getFakeDocument());
    }

    public static Document getFakeDocument(){
        return new Document()
                .id(1L)
                .type("DNI")
                .document("21123123")
                .residenceCountry("Argentina");
    }

    public static PersonEntity getFakePersonEntity(){
        return PersonEntity.builder()
                .id(1L)
                .name("Ignacio")
                .birthdate(LocalDateTime.of(1998,12,19,12,12))
                .document(getFakeDocumentEntity())
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
}
