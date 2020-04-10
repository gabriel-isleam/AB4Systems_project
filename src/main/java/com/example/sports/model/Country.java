package com.example.sports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Document(collection = "Locations")
public class Country {

    @Id
    private String id;
    @Indexed(unique = true)
    private String countryName;
    List<Region> regions;

    public Country(@JsonProperty("countryName") String countryName,
                   @JsonProperty("regions") List<Region> regions) {
        this.countryName = countryName;
        if (regions == null)
            this.regions = new ArrayList<>();
        else
            this.regions = regions;
    }

    public String getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void addRegion(Region region) {
        regions.add(region);
    }

    public int indexOfRegion(String regionName) {
        for (int i = 0; i < regions.size(); i++)
            if (regions.get(i).getRegionName().equals(regionName))
                return i;

        return -1;
    }

   public List<Location> allLocations() {

        List<Location> locations = null;
        for (int i = 0; i < regions.size(); i++) {
            if (locations == null)
                locations = regions.get(i).getLocations();
            else
                locations.addAll(regions.get(i).getLocations());
        }
        return locations;
   }

   public List<Activity> allActivities() {

        List<Location> locations = allLocations();
        List<Activity> activities = null;
        for (int i = 0; i < locations.size(); i++) {
            if (activities == null)
                activities = locations.get(i).getActivities();
            else
                activities.addAll(locations.get(i).getActivities());
        }
        return activities;
   }

   public Map<Activity, String> bestLocations(List<String> sports, String startDay,
                                              String startMonth, String endDay, String endMonth) {

        Map<Activity, String> bestLocations = new TreeMap<>(new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return (int)(o1.getCost() - o2.getCost());
            }
        });
        List<Location> locations = allLocations();
        for (Location location : locations) {
            for (Activity activity : location.getActivities()) {
                if (sports.contains(activity.getSport().getSportName().toLowerCase())) {
                    System.out.println("Exista sportul");
                    if (between(activity, startDay, startMonth, endDay, endMonth)) {
                        bestLocations.put(activity, location.getLocationName());
                        System.out.print("Added");
                    } else {
                        System.out.println("Nu e between");
                    }
                } else {
                    System.out.println("Nu contine sportul");
                }

            }
        }

        return bestLocations;

   }

   public boolean between(Activity activity, String startDay, String startMonth,
                          String endDay, String endMonth) {
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
       Date startDate, endDate;
       try {
           startDate = formatter.parse(startDay + "/" + startMonth);
           endDate = formatter.parse(endDay + "/" + endMonth);
       } catch (ParseException e) {
           return false;
       }
       System.out.println("START_DATE: ");
       System.out.println(startDate);
       System.out.println("END_DATE: ");
       System.out.println(endDate);
       Date actStart = activity.getStartDate(0);
       Date actEnd = activity.getEndDate(0);

       System.out.println("actSTART: ");
       System.out.println(actStart);
       System.out.println("actEND: ");
       System.out.println(actEnd);
       if (actStart.after(actEnd)) {
           if (startDate.after(actStart))
               if (endDate.after(startDate))
                   return true;
               else if (endDate.before(actEnd))
                   return true;
               else
                   return false;
           else {
               if (startDate.before(actEnd)) {
                   if (endDate.before(actEnd))
                       return true;
                   else
                       return false;
               } else
                   return false;
           }
       } else {
            if (startDate.after(actStart) && endDate.before(actEnd))
                return true;
            else
                return false;
       }
   }

}
