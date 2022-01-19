package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
@Builder
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_address_id_seq")
    @SequenceGenerator(allocationSize = 1, sequenceName = "addresses_address_id_id_seq", name = "addresses_address_id_seq")
    @Column(name = "address_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 50, nullable = false)
    private String number;

    @Column()
    private Boolean apartment;

    @OneToOne(optional = false, fetch = FetchType.LAZY, orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    protected AddressEntity() {
        //
    }
}
