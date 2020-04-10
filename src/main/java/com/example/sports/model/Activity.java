package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity {

    private Sport sport;
    private Date startDate;
    private Date endDate;
    private float cost;

    public Activity (Sport sport,
                     @JsonProperty("startDate") Date startDate,
                     @JsonProperty("endDate") Date endDate,
                     float cost) {
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Sport getSport() {
        return sport;
    }

    public String getStartDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        return formatter.format(startDate);
    }

    public String getEndDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM");
        return formatter.format(endDate);
    }

    public Date getStartDate(int x) {
        return startDate;
    }

    public Date getEndDate(int x) {
        return endDate;
    }

    public float getCost() {
        return cost;
    }
}
