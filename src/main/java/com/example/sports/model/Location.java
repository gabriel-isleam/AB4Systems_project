package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class Location {

    private String locationName;
    private List<Activity> activities;

    public Location(@JsonProperty("locationName") String locationName,
                    List<Activity> activities) {
        this.locationName = locationName;
        if (activities == null)
            this.activities = new ArrayList<>();
        else
            this.activities = activities;
    }

    public String getLocationName() {
        return locationName;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }


}
