package com.example.demo.controllers;

import com.example.demo.api.DocumentsApi;
import com.example.demo.converters.DocumentConverter;
import com.example.demo.models.Document;
import com.example.demo.services.interfaces.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentController implements DocumentsApi {

    private final DocumentService documentService;

    @Override
    public ResponseEntity<Document> getDocumentById(final Long documentId) {
        var document = documentService.getDocumentById(documentId)
                .map(DocumentConverter::convertToDocumentDto);

        return document
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Document>> getDocuments() {
        var documents = documentService.getAllDocuments();

        return ResponseEntity.ok(documents.stream().map(DocumentConverter::convertToDocumentDto)
                .collect(Collectors.toList()));
    }
}
