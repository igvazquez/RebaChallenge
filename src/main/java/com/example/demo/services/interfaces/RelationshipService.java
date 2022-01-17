package com.example.demo.services.interfaces;

import com.example.demo.models.PersonEntity;

import java.util.List;

public interface RelationshipService {

    void setParentRelation(final Long parentId, final Long childId);

    List<PersonEntity> getChildren(final Long parentId);
}
