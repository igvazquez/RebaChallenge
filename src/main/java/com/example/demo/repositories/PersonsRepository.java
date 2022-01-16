package com.example.demo.repositories;

import com.example.demo.models.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonsRepository extends CrudRepository<PersonEntity, Long> {

    List<PersonEntity> findAll();

    @Query(value = "SELECT * FROM persons p inner join documents d on d.document_id = p.document_id " +
                    "WHERE d.residence_country = :residence_country and d.type = :type " +
                    "and d.document = :document",
            nativeQuery = true)
    Optional<PersonEntity> findByResidenceCountryAndDocumentTypeAndAndDocument(
            @Param("residence_country") String residenceCountry,
            @Param("type") String documentType,
            @Param("document") String document);
}
