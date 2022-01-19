package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "documents")
@Data
@Builder
@AllArgsConstructor
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_person_id_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "persons_person_id_seq", name = "persons_person_id_seq")
    @Column(name = "document_id")
    private Long id;

    @Column(length = 10, nullable = false)
    private String type;

    @Column(length = 25, nullable = false)
    private String document;

    @Column(name = "residence_country", nullable = false)
    private String residenceCountry;

    @OneToOne(optional = false, fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    protected DocumentEntity() {
        //
    }
}
