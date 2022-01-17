package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.exceptions.IllegalRelationship;
import com.example.demo.repositories.PersonsRepository;
import com.example.demo.services.interfaces.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RelationshipServiceImpl implements RelationshipService {

    private final PersonsRepository personsRepository;

    @Override
    @Transactional
    public void setParentRelation(final Long parentId, final Long childId) {
        if (parentId.equals(childId)){
            throw new IllegalRelationship("A person can't be it's own parent");
        }
        var parentOpt = personsRepository.findById(parentId);

        var child = parentOpt.map( p -> {
            var childOpt = personsRepository.findById(childId);
            return childOpt.map(c -> {
                if (c.getParent() != null){
                    throw new IllegalRelationship("Child with id: "+ childId + " already has a parent");
                }
                if (c.isParentOf(p)){
                    throw new IllegalRelationship("Parent with id: " + parentId + " is already a child of person with id: "+ childId);
                }

                c.setParent(p);
                return c;
            }).orElseThrow(() -> {throw new IllegalRelationship("Child with id: " + childId + " doesn't exists");});
        }).orElseThrow(() -> {throw new IllegalRelationship("Parent with id: " + parentId + " doesn't exists");});

        personsRepository.save(child);
    }

    @Override
    public List<PersonEntity> getChildren(final Long userId) {
        var parentOpt = personsRepository.findById(userId);

        var parent = parentOpt.orElse(null);

        return parent == null ? Collections.emptyList() : parent.getChildren();
    }
}
