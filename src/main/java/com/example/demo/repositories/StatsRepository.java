package com.example.demo.repositories;

import com.example.demo.models.CountryStats;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatsRepository{

//    @Query(value = "SELECT d.residence_country, count(d.document) FROM " +
//                    "documents d GROUP BY residence_country",
//        nativeQuery = true
//    )
//    List<CountryStats> getApiStats();

//    @Query(value = "SELECT new com.example.demo.models.CountryStats(d.residenceCountry, CAST(count(distinct d.document)) as double) FROM " +
//            "DocumentEntity d GROUP BY d.residenceCountry"
//    )
//    List<CountryStats> getApiStats();
//
//    @Query("SELECT count(distinct d.residenceCountry) from DocumentEntity d")
//    Integer countDistinctCountries();

    @PersistenceContext
    EntityManager entityManager;

    public List<CountryStats> getApiStats(){
        var query = entityManager.createQuery(
                "SELECT d.residenceCountry, count(d.document) FROM " +
                "DocumentEntity d GROUP BY d.residenceCountry");

        List<Object[]> stats = query.getResultList();
        return stats.stream().map(r ->
                new CountryStats((String)r[0], Double.valueOf((Long)r[1])))
                .collect(Collectors.toList());
    }
}
