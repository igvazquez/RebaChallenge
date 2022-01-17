package com.example.demo.services;

import com.example.demo.models.Document;
import com.example.demo.models.DocumentEntity;
import com.example.demo.models.Person;
import com.example.demo.models.PersonEntity;
import com.example.demo.models.exceptions.DuplicatePersonException;
import com.example.demo.models.exceptions.IllegalAgeException;
import com.example.demo.repositories.PersonsRepository;
import com.example.demo.services.interfaces.PersonsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static com.example.demo.TestData.getFakePersonEntity;

class PersonsServiceImplTest {

    private final Long USER_ID = 1L;
    private PersonsService personsService;
    private PersonsRepository personsRepository;

    @BeforeEach
    void setUp(){
        personsRepository = mock(PersonsRepository.class);
        personsService = new PersonsServiceImpl(personsRepository);
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