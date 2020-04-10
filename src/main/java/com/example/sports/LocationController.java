package com.example.sports;

import com.example.sports.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/all")
    public List<Country> getAll() {
        return this.locationRepository.findAll();
    }

    @PutMapping
    public void insert(@RequestBody Country country) {
        this.locationRepository.insert(country);
    }

    @PutMapping("/countries")
    public void insertRegion(@RequestParam("country") String countryName,
                              @RequestBody Region region) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        country.addRegion(region);
        this.locationRepository.save(country);
    }

    @PutMapping("/countries/regions")
    public void insertLocation(@RequestParam("country") String countryName,
                               @RequestParam("region") String regionName,
                               @RequestBody  Location location) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        int regionIndex = country.indexOfRegion(regionName);
        if (regionIndex != -1) {
            Region region = country.getRegions().get(regionIndex);
            region.addLocation(location);
            this.locationRepository.save(country);
        }
    }

    @PutMapping("/countries/regions/locations")
    public void insertActivity(@RequestParam("country") String countryName,
                               @RequestParam("region") String regionName,
                               @RequestParam("location") String locationName,
                               @RequestBody Activity activity) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        int regionIndex = country.indexOfRegion(regionName);
        if (regionIndex != -1) {
            Region region = country.getRegions().get(regionIndex);
            int locationIndex = region.indexOfLocation(locationName);
            Location location = region.getLocations().get(locationIndex);
            location.addActivity(activity);
            this.locationRepository.save(country);
        }
    }

    @PostMapping
    public void update(@RequestBody Country country) {
        this.locationRepository.save(country);
    }

    @PostMapping("/countries/region")
    public void updateRegion(@RequestParam("country") String countryName,
                             @RequestBody Region region) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        country.addRegion(region);
        this.locationRepository.save(country);
    }

    @DeleteMapping("/countries/{id}")
    public void deleteById(@PathVariable String id) {
        this.locationRepository.deleteById(id);
    }

    @DeleteMapping("/countries")
    public void deleteByName(@RequestParam("country") String countryName) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        this.locationRepository.delete(country);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        this.locationRepository.deleteAll();
    }

    @DeleteMapping("/countries/regions")
    public void deleteRegion(@RequestParam("country") String countryName,
                             @RequestParam("region") String regionName) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        int regionIndex = country.indexOfRegion(regionName);
        if (regionIndex != -1) {
            country.getRegions().remove(regionIndex);
            this.locationRepository.save(country);
        }
    }

    @DeleteMapping("/countries/regions/locations")
    public void deleteLocation(@RequestParam("country") String countryName,
                               @RequestParam("region") String regionName,
                               @RequestParam("location") String locationName) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        int regionIndex = country.indexOfRegion(regionName);
        if (regionIndex != -1) {
            Region region = country.getRegions().get(regionIndex);
            int locationIndex = region.indexOfLocation(locationName);
            if (locationIndex != -1) {
                region.getLocations().remove(locationIndex);
                this.locationRepository.save(country);
            }
        }
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable("id") String id) {
        Country country = this.locationRepository.findCountryById(id);

        return country;
    }

    @GetMapping("/countries")
    public Country getCountryByName(@RequestParam(name = "country") String name) {
        return this.locationRepository.findCountryByCountryName(name);
    }

    @GetMapping("/countries/regions/locations")
    public List<Location> getLocationsFromCountry(@RequestParam(name = "country") String countryName) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        return country.allLocations();
    }

    @GetMapping("/countries/regions/locations/best")
    public List<BestLocation> getBestLocations(@RequestParam(name = "sports") List<String> sports,
                                               @RequestParam(name = "startDay") String startDay,
                                               @RequestParam(name = "startMonth") String startMonth,
                                               @RequestParam(name = "endDay") String endDay,
                                               @RequestParam(name = "endMonth") String endMonth) {
        List<Country> countries = this.locationRepository.findAll();
        Map<Activity, String> map;
        List<BestLocation> bestLocations = new ArrayList<>();
        for (Country country : countries) {
            map = country.bestLocations(sports, startDay, startMonth, endDay, endMonth);
            for(Map.Entry<Activity,String> entry : map.entrySet()) {
                Activity activity = entry.getKey();
                bestLocations.add(new BestLocation(entry.getValue(), activity.getSport().getSportName(),
                        activity.getCost()));
            }
        }

        return bestLocations;
    }
}
