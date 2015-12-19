package com.oandmdigital.mappingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

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
    private double mDistance;


    public Shop(String name,
                Address address,
                List<OpeningTime> openingTimes,
                String url,
                String telephone,
                String imageUrl,
                double rating,
                double latitude,
                double longitude,
                double distance) {

        mName = name;
        mAddress = address;
        mOpeningTimes = openingTimes;
        mUrl = url;
        mTelephone = telephone;
        mImageUrl = imageUrl;
        mRating = rating;
        mLatitude = latitude;
        mLongitude = longitude;
        mDistance = distance;
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

    public double getDistance() {
        return mDistance;
    }


    @Override
    public String toString() {
        return String.format("%s, %s %s", getName(), getAddress().getStreet(), getAddress().getArea());
    }



}
