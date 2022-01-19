package com.example.demo.repositories;

import com.example.demo.models.DocumentEntity;
import com.example.demo.models.PhoneEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository extends CrudRepository<PhoneEntity, Long> {

    List<PhoneEntity> findAll();

    @Query(value = "SELECT * FROM phones ph inner join persons p " +
            "on p.person_id = ph.person_id where p.person_id = :personId",
            nativeQuery = true)
    Optional<PhoneEntity> findPhoneByPersonId(@Param("personId")final Long personId);
}
