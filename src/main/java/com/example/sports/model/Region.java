package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class Region {

    String regionName;
    List<Location> locations;

    public Region(@JsonProperty("regionName") String regionName,
                  @JsonProperty("locations") List<Location> locations) {
        this.regionName = regionName;
        if (locations == null)
            this.locations = new ArrayList<>();
        else
            this.locations = locations;
    }

    public String getRegionName() {
        return regionName;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public int indexOfLocation(String locationName) {
        for (int i = 0; i < locations.size(); i++)
            if (locations.get(i).getLocationName().equals(locationName))
                return i;

        return -1;
    }
}
