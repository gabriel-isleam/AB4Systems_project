package com.example.sports;

import com.example.sports.model.Country;
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

    @PostMapping
    public void update(@RequestBody Country country) {
        this.locationRepository.save(country);
    }

    @PostMapping("/country={countryName}")
    public void addRegion(@PathVariable("countryName") String countryName,
                          @RequestBody Region region) {
        Country country = this.locationRepository.findCountryByCountryName(countryName);
        country.addRegion(region);
        this.locationRepository.save(country);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.locationRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        this.locationRepository.deleteAll();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable("id") String id) {
        Country country = this.locationRepository.findCountryById(id);

        return country;
    }

    @GetMapping("/country/{name}")
    public List<Country> getSmallerCountries(@PathVariable("name") String name) {
        List<Country> countries = this.locationRepository.findCountriesByCountryNameAfterOrderByCountryName(name);

        return countries;
    }
}
