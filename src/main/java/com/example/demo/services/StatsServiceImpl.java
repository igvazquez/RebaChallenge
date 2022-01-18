package com.example.demo.services;

import com.example.demo.models.CountryStats;
import com.example.demo.repositories.DocumentsRepository;
import com.example.demo.repositories.PersonsRepository;
import com.example.demo.repositories.StatsRepository;
import com.example.demo.services.interfaces.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatsServiceImpl implements StatsService {

    private final StatsRepository statsRepository;
    private final PersonsRepository personsRepository;

    @Override
    public List<CountryStats> getApiStats() {
        return statsRepository.getApiStats();
    }

    @Override
    public Long getTotalPersons() {
        return personsRepository.count();
    }
}
