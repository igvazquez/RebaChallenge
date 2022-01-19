package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import com.example.demo.models.exceptions.IllegalRelationship;
import com.example.demo.repositories.PersonsRepository;
import com.example.demo.services.interfaces.RelationshipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.example.demo.TestData.getFakePersonEntity;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelationshipServiceImplTest {

    private PersonsRepository personsRepository;
    private RelationshipService relationshipService;

    private PersonEntity grandFather;
    private PersonEntity father;
    private PersonEntity son1;
    private PersonEntity son2;

    @BeforeEach
    void setUp(){
        personsRepository = mock(PersonsRepository.class);
        relationshipService = new RelationshipServiceImpl(personsRepository);

        grandFather = getFakePersonEntity();
        father = getFakePersonEntity();
        son1 = getFakePersonEntity();
        son2 = getFakePersonEntity();

        grandFather.setChildren(List.of(father));
        father.setChildren(List.of(son1, son2));

        father.setParent(grandFather);
        son1.setParent(father);
        son2.setParent(father);

        father.setId(2L);
        son1.setId(3L);
        son2.setId(4L);
    }

    @Test
    void testSetParentRelation(){
        var p = getFakePersonEntity();
        var c = getFakePersonEntity();
        c.setId(2L);

        when(personsRepository.findById(p.getId())).thenReturn(Optional.of(p));
        when(personsRepository.findById(c.getId())).thenReturn(Optional.of(c));

        relationshipService.setParentRelation(p.getId(), c.getId());

        Assertions.assertNotNull(c.getParent());
        Assertions.assertEquals(p, c.getParent());
    }

    @Test
    void testSetChildAsParent(){
        when(personsRepository.findById(grandFather.getId())).thenReturn(Optional.of(grandFather));
        when(personsRepository.findById(father.getId())).thenReturn(Optional.of(father));

        var e = Assertions.assertThrows(IllegalRelationship.class,
                () -> relationshipService.setParentRelation(father.getId(), grandFather.getId()));

        var expectedMsg = "Parent with id: " + father.getId() + " is already a child of person with id: "+ grandFather.getId();
        Assertions.assertEquals(expectedMsg, e.getMessage());
    }

    @Test
    void testSetChildWithParent(){
        when(personsRepository.findById(son1.getId())).thenReturn(Optional.of(son1));
        when(personsRepository.findById(son2.getId())).thenReturn(Optional.of(son2));

        var e = Assertions.assertThrows(IllegalRelationship.class,
                () -> relationshipService.setParentRelation(son1.getId(), son2.getId()));

        var expectedMsg = "Child with id: "+ son2.getId() + " already has a parent";
        Assertions.assertEquals(expectedMsg, e.getMessage());
    }

    @Test
    void testChildNotExists(){
        var fakeId = 10L;
        when(personsRepository.findById(son1.getId())).thenReturn(Optional.of(son1));
        when(personsRepository.findById(fakeId)).thenReturn(Optional.empty());

        var e = Assertions.assertThrows(IllegalRelationship.class,
                () -> relationshipService.setParentRelation(son1.getId(), fakeId));

        var expectedMsg = "Child with id: " + fakeId + " doesn't exists";
        Assertions.assertEquals(expectedMsg, e.getMessage());
    }

    @Test
    void testParentNotExists(){
        var fakeId = 10L;
        when(personsRepository.findById(son1.getId())).thenReturn(Optional.of(son1));
        when(personsRepository.findById(fakeId)).thenReturn(Optional.empty());

        var e = Assertions.assertThrows(IllegalRelationship.class,
                () -> relationshipService.setParentRelation(fakeId, son1.getId()));

        var expectedMsg = "Parent with id: " + fakeId + " doesn't exists";
        Assertions.assertEquals(expectedMsg, e.getMessage());
    }
}