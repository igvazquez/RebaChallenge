package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
@Builder
@AllArgsConstructor
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phones_phone_id_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "phones_phone_id_seq", name = "phones_phone_id_seq")
    @Column(name = "phone_id")
    private Long id;

    @Column(length = 25, nullable = false)
    private String number;

    @OneToOne(optional = false, fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    protected PhoneEntity() {
        //
    }
}
