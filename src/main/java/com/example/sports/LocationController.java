package com.example.sports;

import com.example.sports.model.Activity;
import com.example.sports.model.Country;
import com.example.sports.model.Location;
import com.example.sports.model.Region;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Country getCountryByName(@RequestParam(name = "name") String name) {
        Country country = this.locationRepository.findCountryByCountryName(name);
        return country;
    }

    @GetMapping("/countries/regions/locations")
    public List<Location> getLocationsFromCountry(@RequestParam(name = "country") String countryName) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        return country.allLocations();
    }
}
