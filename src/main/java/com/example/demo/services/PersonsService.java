package com.example.demo.services;

import com.example.demo.models.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonsService {

    List<PersonEntity> getAllPersons();

    Optional<PersonEntity> getPersonById(final Long userId);

    void deletePersonById(final Long userId);

    PersonEntity updatePersonById(final Long userId, final PersonEntity person);

    PersonEntity postPerson(final PersonEntity person);

    boolean personExists(final PersonEntity person);
}
