package com.example.demo.services;

import com.example.demo.models.exceptions.DuplicatePersonException;
import com.example.demo.models.exceptions.IllegalRelationship;
import com.example.demo.repositories.PersonsRepository;
import com.example.demo.services.interfaces.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RelationshipServiceImpl implements RelationshipService {

    private final PersonsRepository personsRepository;

    @Override
    public void setParentRelation(Long parentId, Long childId) {
        var parentOpt = personsRepository.findById(parentId);

        var parent = parentOpt.map( p -> {
            var childOpt = personsRepository.findById(childId);
            childOpt.ifPresentOrElse(p::setChild,
                    () -> {throw new IllegalRelationship("Child with id: " + childId + " doesn't exists");}
            );
            return p;
        }).orElseThrow(() -> {throw new IllegalRelationship("Parent with id: " + parentId + " doesn't exists");});

        personsRepository.save(parent);
    }
}
