package com.oandmdigital.mappingapp.model;

import android.graphics.drawable.Drawable;

import java.util.List;

public class Shop {

    private String mName;
    private Address mAddress;
    private List<OpeningTime> mOpeningTimes;
    private String mUrl;
    private String mTelephone;
    private String mImageUrl;
    private double mRating;
    private double mLongitude;
    private double mLatitude;


    public Shop(String name,
                Address address,
                List<OpeningTime> openingTimes,
                String url,
                String telephone,
                String imageUrl,
                double rating,
                double longitude,
                double latitude) {

        mName = name;
        mAddress = address;
        mOpeningTimes = openingTimes;
        mUrl = url;
        mTelephone = telephone;
        mImageUrl = imageUrl;
        mRating = rating;
        mLongitude = longitude;
        mLatitude = latitude;
    }


    public String getName() {
        return mName;
    }

    public Address getAddress() {
        return mAddress;
    }

    public List<OpeningTime> getOpeningTimes() {
        return mOpeningTimes;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public double getRating() {
        return mRating;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public double getLatitude() {
        return mLatitude;
    }


    @Override
    public String toString() {
        return String.format("%s, %s %s", getName(), getAddress().getStreet(), getAddress().getArea());
    }
}
