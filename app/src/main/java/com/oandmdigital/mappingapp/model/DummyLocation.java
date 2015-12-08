package com.oandmdigital.mappingapp.model;

public class DummyLocation {

    private String mStreet;
    private double mLongitude;
    private double mLatitude;


    public DummyLocation(String street, double longitude, double latitude) {
        mStreet = street;
        mLongitude = longitude;
        mLatitude = latitude;
    }


    public String getStreet() {
        return mStreet;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }
}
