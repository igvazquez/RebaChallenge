package com.example.demo.controllers;

import com.example.demo.api.PersonsApi;
import com.example.demo.converters.PersonsConverter;
import com.example.demo.models.Person;
import com.example.demo.services.PersonsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonsController implements PersonsApi {

    private final PersonsServiceImpl personsService;

    @Autowired
    public PersonsController(PersonsServiceImpl personsService) {
        this.personsService = personsService;
    }

    @Override
    public ResponseEntity<List<Person>> getAllPersons() {
        var persons = personsService.getAllPersons();

        return ResponseEntity.ok(persons.stream().map(PersonsConverter::convertToPersonDto)
                .collect(Collectors.toList()));
    }
}
