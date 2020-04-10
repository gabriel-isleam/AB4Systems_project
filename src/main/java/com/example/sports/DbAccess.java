package com.example.sports;

import com.example.sports.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class DbAccess implements CommandLineRunner {

    private LocationRepository locationRepository;

    public DbAccess(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // for testing we will delete all entries and insert new data
        this.locationRepository.deleteAll();
        insertData("Romania", "Predeal", 200);
        insertData("Bulgaria", "Bansko", 100);

    }

    public void insertData(String countryName, String locationName, int cost) {
        Sport ski = new Sport("Ski", Arrays.asList("skis", "helmet"));
        Sport atv = new Sport("ATV", Arrays.asList("atv", "helmet", "driving licence"));
        Sport paragliding = new Sport("Paragliding", Arrays.asList("paraglider", "helmet"));

        Date startDate, endDate;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        try {
            startDate = formatter.parse("01" + "/" + "01");
            endDate = formatter.parse("31" + "/" + "12");
        } catch (ParseException e) {
            System.out.println("Wrong start/end date format");
            startDate = null;
            endDate = null;
        }

        Activity skiActivity = new Activity(ski, startDate, endDate, cost);
        Activity atvActivity = new Activity(atv, startDate, endDate, cost + 150);
        Activity paragActivity = new Activity(paragliding, startDate, endDate, cost - 50);

        Location location = new Location(locationName, Arrays.asList(skiActivity, atvActivity, paragActivity));

        Region region = new Region("Brasov", Arrays.asList(location));

        Country country = new Country(countryName, Arrays.asList(region));

        // insert first data
        this.locationRepository.save(country);
    }
}
