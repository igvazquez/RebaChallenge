package com.example.demo.services.interfaces;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.RelativeRelation;

import java.util.List;

public interface RelationshipService {

    void setParentRelation(final Long parentId, final Long childId);

    List<PersonEntity> getChildren(final Long parentId);

    RelativeRelation getRelationBetween(final Long userId1, final Long userId2);
}
