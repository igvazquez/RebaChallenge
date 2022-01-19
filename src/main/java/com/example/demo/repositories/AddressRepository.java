package com.example.demo.repositories;

import com.example.demo.models.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    @Query(value = "SELECT * FROM addresses a inner join persons p " +
            "on p.person_id = a.person_id where p.person_id = :personId",
            nativeQuery = true)
    Optional<AddressEntity> findAddressByPersonId(@Param("personId")final Long personId);

    List<AddressEntity> findAll();
}
