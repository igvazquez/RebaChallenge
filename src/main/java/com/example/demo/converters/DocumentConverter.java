package com.example.demo.converters;

import com.example.demo.models.Document;
import com.example.demo.models.DocumentEntity;

public final class DocumentConverter {

    private DocumentConverter(){
        // Utility class
    }


    public static Document convertToDocumentDto(final DocumentEntity document) {
        return new Document()
                .id(document.getId())
                .type(document.getType())
                .document(document.getDocument())
                .residenceCountry(document.getResidenceCountry());
    }

    public static DocumentEntity convertToDocumentEntity(final Document document) {
        return DocumentEntity.builder()
                .id(document.getId())
                .type(document.getType())
                .document(document.getDocument())
                .residenceCountry(document.getResidenceCountry())
                .build();
    }
}
