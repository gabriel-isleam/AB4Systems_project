package com.example.sports;

import com.example.sports.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Country, String> {

    Country findCountryById(String id);
    List<Country> findCountriesByCountryNameAfterOrderByCountryName(String countryName);
    Country findCountryByCountryName(String countryName);
}
