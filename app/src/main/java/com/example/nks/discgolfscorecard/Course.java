package com.example.nks.discgolfscorecard;

import java.util.ArrayList;

public class Course {
    private double latitude_;
    private double longitude_;
    private String city_;
    private int holes_;
    private ArrayList<Hole> course_;

    public Course(int holes) {
        course_ = new ArrayList<Hole>();
        latitude_ = 0;
        longitude_ = 0;
        city_ = null;
        holes_ = holes;
    }

    public void addHole(Hole hole) {
        course_.add(hole);
        holes_++;
    }

    public double getLatitude() {
        return latitude_;
    }

    public double getLongitude() {
        return longitude_;
    }

    public Hole getHole(int hole_number) {
        return course_.get(hole_number);
    }

    public ArrayList<Hole> getCourse() {
        return course_;
    }

    public int getHoles() {
        return holes_;
    }

    public void setCoordinates(double latitude, double longitude) {
        latitude_ = latitude;
        longitude_ = longitude;
    }

    public void setCity(String cityname) {
        city_ = cityname;
    }

    public double getDistanceToCurrentLocation(double latitude, double longitude) {
        return Math.sqrt((Math.abs(Math.pow(latitude_ -latitude,2)+Math.pow(longitude_ -longitude,2))));
    }
}
