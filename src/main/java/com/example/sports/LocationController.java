package com.example.sports;

import com.example.sports.model.Country;
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.locationRepository.deleteById(id);
    }
}
