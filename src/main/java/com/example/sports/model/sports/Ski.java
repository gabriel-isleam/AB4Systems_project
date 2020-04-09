package com.example.sports.model.sports;

import com.example.sports.model.Sport;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Ski extends Sport {

    public Ski(@JsonProperty("ecquipment") List<String> equipment) {
        super("Ski", equipment);
    }
}
