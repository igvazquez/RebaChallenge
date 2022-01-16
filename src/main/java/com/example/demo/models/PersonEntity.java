package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
@Data
@Builder
@AllArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_person_id_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "persons_person_id_seq", name = "persons_person_id_seq")
    @Column(name = "person_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthdate;

    @Column(name = "residence_country", nullable = false)
    private String residenceCountry;

    @OneToOne(optional = false, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    protected PersonEntity() {
        //
    }
}
