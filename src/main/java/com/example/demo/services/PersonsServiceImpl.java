package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.exceptions.DuplicatePersonException;
import com.example.demo.models.exceptions.IllegalAgeException;
import com.example.demo.models.exceptions.PersonNotExistsException;
import com.example.demo.repositories.PersonsRepository;
import com.example.demo.services.interfaces.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonsServiceImpl implements PersonsService {

    private static final int LEGAL_AGE = 18;

    private final PersonsRepository personsRepository;

    @Override
    public List<PersonEntity> getAllPersons() {
        return personsRepository.findAll();
    }

    @Override
    public Optional<PersonEntity> getPersonById(final Long userId) {
        return personsRepository.findById(userId);
    }

    @Override
    public void deletePersonById(final Long userId) {
        try{
            personsRepository.deleteById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new PersonNotExistsException(userId);
        }
    }

    @Override
    @Transactional
    public PersonEntity updatePersonById(final Long userId, final PersonEntity person) {
        var oldPerson = personsRepository.findById(userId);

        return oldPerson.map((oldP) -> {
            if (person.getName() != null){
                oldP.setName(person.getName());
            }
            if (person.getBirthdate() != null && isValidAge(person)){
                oldP.setBirthdate(person.getBirthdate());
            }

            return personsRepository.save(oldP);
        }).orElseGet(() -> {
            person.setId(userId);
            return postPerson(person);
        });
    }

    @Override
    @Transactional
    public PersonEntity postPerson(final PersonEntity person) {
        if (!isValidAge(person)){
            throw new IllegalAgeException();
        }

        if (personExists(person)){
            throw new DuplicatePersonException(person);
        }

        return personsRepository.save(person);
    }

    private boolean isValidAge(final PersonEntity person) {
        return ChronoUnit.YEARS.between(person.getBirthdate(), LocalDateTime.now()) >= LEGAL_AGE;
    }

    @Override
    public boolean personExists(final PersonEntity person) {
        return personsRepository
                .findByResidenceCountryAndDocumentTypeAndAndDocument(
                        person.getDocument().getResidenceCountry(),
                        person.getDocument().getType(),
                        person.getDocument().getDocument())
                .isPresent();
    }
}
