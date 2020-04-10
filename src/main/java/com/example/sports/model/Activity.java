package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Activity {

    private Sport sport;
    private String startDay;
    private String startMonth;
    private String endDay;
    private String endMonth;
    private float cost;

    public Activity (Sport sport,
                     @JsonProperty("startDay") String startDay,
                     @JsonProperty("startMonth") String startMonth,
                     @JsonProperty("endDay") String endDay,
                     @JsonProperty("endMonth") String endMonth,
                     float cost) {
        this.sport = sport;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.cost = cost;
    }

    public Sport getSport() {
        return sport;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getEndDay() {
        return endDay;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public float getCost() {
        return cost;
    }
}
