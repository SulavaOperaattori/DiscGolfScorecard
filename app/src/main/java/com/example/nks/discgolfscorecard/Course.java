package com.example.nks.discgolfscorecard;

import java.util.ArrayList;

import static com.example.nks.discgolfscorecard.R.string.hole;

public class Course {
    private double latitude;
    private double longitude;
    private String city;
    private int holes;
    private ArrayList<Hole> course;

    public Course(int holes) {
        course = new ArrayList<Hole>();
        latitude = 0;
        longitude = 0;
        city = null;
    }

    public void addHole(Hole hole) {
        course.add(hole);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Hole getHole(int hole_number) {
        return course.get(hole_number);
    }

    public ArrayList<Hole> getCourse() {
        return course;
    }

    public int getHoles() {
        return holes;
    }

    public void setCoordinates(double latitude_p, double longitude_p) {
        latitude = latitude_p;
        longitude = longitude_p;
    }

    public void setCity(String cityname) {
        city = cityname;
    }

    public double getDistanceToCurrentLocation(double latitude_p, double longitude_p) {
        return Math.sqrt((Math.abs(Math.pow(latitude-latitude_p,2)+Math.pow(longitude-longitude_p,2))));
    }
}
