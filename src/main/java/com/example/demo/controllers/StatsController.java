package com.example.demo.controllers;

import com.example.demo.api.StatsApi;
import com.example.demo.models.CountryStats;
import com.example.demo.models.CountryStatsDTO;
import com.example.demo.services.interfaces.PersonsService;
import com.example.demo.services.interfaces.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatsController implements StatsApi {

    private final StatsService statsService;

    @Override
    public ResponseEntity<List<CountryStatsDTO>> getStats() {
        var stats = statsService.getApiStats();

        var totalPersons = statsService.getTotalPersons();

        var statsDto = stats.stream()
                .map( s -> new CountryStatsDTO()
                        .country(s.getCountry())
                        .percentage(100*s.getPersons()/totalPersons))
                .collect(Collectors.toList());

        return ResponseEntity.ok(statsDto);
    }
}
