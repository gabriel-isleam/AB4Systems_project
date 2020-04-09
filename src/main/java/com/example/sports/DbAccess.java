package com.example.sports;

import com.example.sports.model.Country;
import com.example.sports.model.Region;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbAccess implements CommandLineRunner {

    private LocationRepository locationRepository;

    public DbAccess(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        Country romania = new Country("ROMANIA");
//        Country france = new Country("FRANCE");

            this.locationRepository.deleteAll();
//
//        List<Country> locations = Arrays.asList(romania, france);
//
//        this.locationRepository.saveAll(locations);
            Region region = new Region("Constanta");
            Country country = new Country("Romania", Arrays.asList(region));

            this.locationRepository.save(country);

    }
}
