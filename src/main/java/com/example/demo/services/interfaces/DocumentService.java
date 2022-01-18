package com.example.demo.services.interfaces;

import com.example.demo.models.DocumentEntity;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<DocumentEntity> getAllDocuments();

    Optional<DocumentEntity> getDocumentById(final Long documentId);

    Optional<DocumentEntity> getDocumentByUserId(final Long userId);
}
