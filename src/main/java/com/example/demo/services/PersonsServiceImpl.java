package com.example.demo.services;

import com.example.demo.models.PersonEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsServiceImpl implements PersonsService {

    @Override
    public List<PersonEntity> getAllPersons() {
        return null;
    }
}
