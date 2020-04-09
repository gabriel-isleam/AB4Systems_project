package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Region {

    String regionName;
    List<Location> locations;

    public Region(@JsonProperty("regionName") String regionName,
                  @JsonProperty("locations") List<Location> locations) {
        this.regionName = regionName;
        this.locations = locations;
    }

    public String getRegionName() {
        return regionName;
    }
}
