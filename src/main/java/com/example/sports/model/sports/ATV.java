package com.example.sports.model.sports;

import com.example.sports.model.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ATV extends Sport {

    public ATV(@JsonProperty("ecquipment") List<String> equipment) {
        super("ATV", equipment);
    }
}
