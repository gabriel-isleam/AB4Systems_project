package com.example.sports;

import com.example.sports.model.Activity;
import com.example.sports.model.Country;
import com.example.sports.model.Location;
import com.example.sports.model.Region;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Country, String> {

    Country findCountryById(String id);
    Country findCountryByCountryName(String countryName);

}
