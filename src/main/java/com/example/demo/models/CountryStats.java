package com.example.demo.models;

import lombok.Data;

@Data
public class CountryStats {

    private String country;
    private Double persons;

    public CountryStats(final String country, final Double persons) {
        this.country = country;
        this.persons = persons;
    }
}
