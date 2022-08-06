package com.example.mapapp;

import android.content.Context;

import org.osmdroid.util.GeoPoint;

public class MapInfoSet {
    public String title;
    public String description;
    public GeoPoint  position;
    public Integer image;



    public MapInfoSet(){

        this.image = 0;
        this.title = "";
        this.description ="";
        this.position =new GeoPoint(35,135);
    }

    public MapInfoSet(Integer iimage,String ititle,String idescription,GeoPoint iPoint){

        this.image = iimage;
        this.title = ititle;
        this.description =idescription;
        this.position =iPoint;
    }
}



