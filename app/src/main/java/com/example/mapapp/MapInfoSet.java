package com.example.mapapp;

import android.content.Context;

import org.osmdroid.util.GeoPoint;

public class MapInfoSet {
    public String title;
    public String description;
    public GeoPoint  position;

    public MapInfoSet(){

        title = "";
        description = "";
        position = new GeoPoint(35.,135.);
    }
}
