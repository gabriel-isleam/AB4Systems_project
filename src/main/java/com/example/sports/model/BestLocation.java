package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BestLocation {

    private String locationName;
    private String sportName;
    private float cost;

    public BestLocation(@JsonProperty("locationName") String locationName,
                        @JsonProperty("sportName") String sportName,
                        @JsonProperty("cost") float cost) {
        this.locationName = locationName;
        this.sportName = sportName;
        this.cost = cost;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getSportName() {
        return sportName;
    }

    public float getCost() {
        return cost;
    }
}
