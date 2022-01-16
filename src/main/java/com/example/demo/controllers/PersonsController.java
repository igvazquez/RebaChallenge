package com.example.demo.controllers;

import com.example.demo.api.PersonsApi;
import com.example.demo.converters.DocumentConverter;
import com.example.demo.converters.PersonsConverter;
import com.example.demo.models.Document;
import com.example.demo.models.Person;
import com.example.demo.models.exceptions.PersonNotExistsException;
import com.example.demo.services.DocumentService;
import com.example.demo.services.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonsController implements PersonsApi {

    private final PersonsService personsService;
    private final DocumentService documentService;

    @Override
    public ResponseEntity<List<Person>> getAllPersons() {
        var persons = personsService.getAllPersons();

        return ResponseEntity.ok(persons.stream().map(PersonsConverter::convertToPersonDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Person> postPerson(final Person body) {
        var person = personsService.postPerson(PersonsConverter.convertToEntity(body));

        return ResponseEntity.created(URI.create("/persons/" + person.getId())).body(body);
    }

    @Override
    public ResponseEntity<Void> deletePersonById(final Long userId) {

        try{
            personsService.deletePersonById(userId);
        }catch (PersonNotExistsException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Person> getPersonById(final Long userId) {
        var person = personsService.getPersonById(userId)
                .map(PersonsConverter::convertToPersonDto);

        return person
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Person> updatePersonById(final Long userId, final Person body) {
        var person = personsService.updatePersonById(userId,
                PersonsConverter.convertToEntity(body));
        return ResponseEntity.ok(PersonsConverter.convertToPersonDto(person));
    }

    @Override
    public ResponseEntity<Document> getDocumentByUserId(Long userId) {
        var document = documentService.getDocumentByUserId(userId)
                .map(DocumentConverter::convertToDocumentDto);

        return document
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
