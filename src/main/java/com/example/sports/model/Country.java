package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
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
        if (regions == null)
            this.regions = new ArrayList<>();
        else
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

    public int indexOfRegion(String regionName) {
        for (int i = 0; i < regions.size(); i++)
            if (regions.get(i).getRegionName().equals(regionName))
                return i;

        return -1;
    }

   public List<Location> allLocations() {

        List<Location> locations = null;
        for (int i = 0; i < regions.size(); i++) {
            if (locations == null)
                locations = regions.get(i).getLocations();
            else
                locations.addAll(regions.get(i).getLocations());
        }
        return locations;
   }

}
