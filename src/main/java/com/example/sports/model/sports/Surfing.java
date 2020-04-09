package com.example.sports.model.sports;

import com.example.sports.model.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Surfing extends Sport {

    public Surfing(@JsonProperty("ecquipment") List<String> equipment) {
        super("Surfing", equipment);
    }
}
