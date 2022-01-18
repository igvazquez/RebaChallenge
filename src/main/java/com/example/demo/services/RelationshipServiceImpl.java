package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.Relations;
import com.example.demo.models.RelativeRelation;
import com.example.demo.models.exceptions.IllegalRelationship;
import com.example.demo.models.exceptions.PersonNotExistsException;
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

    @Override
    public RelativeRelation getRelationBetween(final Long userId1, final Long userId2) {
        var p1 = personsRepository.findById(userId1)
                .orElseThrow(() -> new PersonNotExistsException(userId1));
        var p2 = personsRepository.findById(userId2)
                .orElseThrow(() -> new PersonNotExistsException(userId2));

        var rel = "no relation";

        if (p1.isParentOf(p2)){
            rel = Relations.PARENT.getRelation();
        }else if (p1.isSiblingOf(p2)){
            rel = Relations.SIBLINGS.getRelation();
        }else if (p1.isCousinOf(p2)){
            rel = Relations.COUSINS.getRelation();
        }else if(p1.isUncleOf(p2)){
            rel = Relations.UNCLE.getRelation();
        }else if(p1.isNephewOf(p2)){
            rel = Relations.NEPHEW.getRelation();
        }

        return new RelativeRelation(p1, p2, rel);
    }
}
