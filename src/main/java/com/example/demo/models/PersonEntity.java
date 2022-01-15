package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_person_id_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "persons_person_id_seq", name = "persons_person_id_seq")
    @Column(name = "person_id")
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    @Basic(optional = false)
    private LocalDateTime birthdate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }
}
