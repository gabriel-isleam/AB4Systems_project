package com.example.sports;

import com.example.sports.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DbAccess implements CommandLineRunner {

    private LocationRepository locationRepository;

    public DbAccess(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        this.locationRepository.deleteAll();

        Sport ski = new Ski(Arrays.asList("skis", "helmet"));
        Sport atv = new ATV(Arrays.asList("atv", "helmet", "driving licence"));

        Activity skiActivity = new Activity(ski, "12", "01", "3", "01", 100); //MonthDay.of(12, 1), MonthDay.of(3, 1));
        Activity atvActivity = new Activity(atv, "01","01", "12"," 31", 200);//MonthDay.of(1, 1), MonthDay.of(12, 31));

        Location location = new Location("Predeal", Arrays.asList(skiActivity, atvActivity));

        Region region = new Region("Brasov", Arrays.asList(location));

        Country country = new Country("Romania", Arrays.asList(region));

        this.locationRepository.save(country);

    }
}
