package com.example.demo.services;

import com.example.demo.models.DocumentEntity;
import com.example.demo.repositories.DocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentServiceImpl implements DocumentService{

    private final DocumentsRepository documentsRepository;

    @Override
    public List<DocumentEntity> getAllDocuments() {
        return documentsRepository.findAll();
    }

    @Override
    public Optional<DocumentEntity> getDocumentById(final Long documentId) {
        return documentsRepository.findById(documentId);
    }

    @Override
    public Optional<DocumentEntity> getDocumentByUserId(final Long userId) {
        return documentsRepository.findDocumentByUserId(userId);
    }
}
