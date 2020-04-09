package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Locations")
public class Country {

    @Id
    private String id;
    @Indexed(unique = true)
    private String countryName;
    List<Region> regions;

    public Country(@JsonProperty("countryName") String countryName,
                   @JsonProperty("regions") List<Region> regions) {
        this.countryName = countryName;
        this.regions = regions;
    }

    public String getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void addRegion(Region region) {
        regions.add(region);
    }
}
