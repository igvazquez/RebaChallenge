package com.example.demo.services.interfaces;

import com.example.demo.models.CountryStats;

import java.util.List;

public interface StatsService {

    List<CountryStats> getApiStats();

    Long getTotalPersons();
}
