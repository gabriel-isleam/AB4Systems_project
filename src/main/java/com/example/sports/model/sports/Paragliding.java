package com.example.sports.model.sports;

import com.example.sports.model.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Paragliding extends Sport {

    public Paragliding(@JsonProperty("ecquipment") List<String> equipment) {
        super("Paragliding", equipment);
    }
}
