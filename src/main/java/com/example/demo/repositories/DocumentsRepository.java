package com.example.demo.repositories;

import com.example.demo.models.DocumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentsRepository extends CrudRepository<DocumentEntity, Long> {

    List<DocumentEntity> findAll();

    @Query(value = "SELECT * FROM documents d inner join persons p on d.document_id = p.document_id " +
            "WHERE p.person_id = :userId",
            nativeQuery = true)
    Optional<DocumentEntity> findDocumentByUserId(@Param("userId") Long userId);
}
