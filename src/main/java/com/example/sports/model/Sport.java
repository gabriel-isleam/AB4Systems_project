package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Sport {

    private String sportName;
    private List<String> equipment;

    public Sport(@JsonProperty("sportName") String sportName,
                 @JsonProperty("ecquipment") List<String> equipment) {
        this.sportName = sportName;
        this.equipment = equipment;
    }

    public String getSportName() {
        return sportName;
    }

    public List<String> getEquipment() {
        return equipment;
    }
}
