package com.example.demo.services;

import com.example.demo.models.Document;
import com.example.demo.models.DocumentEntity;
import com.example.demo.models.Person;
import com.example.demo.models.PersonEntity;
import com.example.demo.models.exceptions.DuplicatePersonException;
import com.example.demo.models.exceptions.IllegalAgeException;
import com.example.demo.repositories.PersonsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonsServiceImplTest {

    private final Long USER_ID = 1L;
    private PersonsService personsService;
    private PersonsRepository personsRepository;

    @BeforeEach
    void setUp(){
        personsRepository = mock(PersonsRepository.class);
        personsService = new PersonsServiceImpl(personsRepository);
    }

    Person getFakePerson(){
        return new Person()
                .id(1L)
                .name("Ignacio")
                .birthdate(LocalDateTime.of(1998,19,12,12,12).toString())
                .residenceCountry("Argentina")
                .document(getFakeDocument());
    }

    Document getFakeDocument(){
        return new Document()
                .id(1L)
                .type("DNI")
                .document("21123123");
    }

    PersonEntity getFakePersonEntity(){
        return PersonEntity.builder()
                .id(1L)
                .name("Ignacio")
                .birthdate(LocalDateTime.of(1998,12,19,12,12))
                .residenceCountry("Argentina")
                .document(getFakeDocumentEntity())
                .build();
    }

    DocumentEntity getFakeDocumentEntity(){
        return DocumentEntity.builder()
                .id(1L)
                .type("DNI")
                .document("21123123")
                .build();
    }

    @Test
    void getAllPersonsTest() {
        when(personsRepository.findAll()).thenReturn(List.of(getFakePersonEntity(), getFakePersonEntity()));

        var ret = personsService.getAllPersons();

        Assertions.assertEquals(2, ret.size(), "Expected list size is correct");
        Assertions.assertEquals(getFakePersonEntity(), ret.get(0), "Expected entity is correct");
    }

    @Test
    void getPersonByIdTest() {
        when(personsRepository.findById(USER_ID)).thenReturn(Optional.of(getFakePersonEntity()));

        var ret = personsService.getPersonById(USER_ID);

        Assertions.assertTrue(ret.isPresent(), "Expected entity is present");
        Assertions.assertEquals(getFakePersonEntity(), ret.get(), "Expected entity is correct");
    }

    @Test
    void postUnderAgePersonTest(){
        var p = getFakePersonEntity();
        p.setBirthdate(LocalDateTime.now());

        Assertions.assertThrows(IllegalAgeException.class, () -> personsService.postPerson(p));
    }

    @Test
    void postPersonTest(){
        var p = getFakePersonEntity();

        when(personsRepository
                .findByResidenceCountryAndDocumentTypeAndAndDocument(any(), any(), any()))
                .thenReturn(Optional.ofNullable(getFakePersonEntity()));

        Assertions.assertThrows(DuplicatePersonException.class, () -> personsService.postPerson(p));
    }
}