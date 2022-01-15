package com.example.demo.repositories;

import com.example.demo.models.PersonEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository {


    List<PersonEntity> findAllPersons();
}
