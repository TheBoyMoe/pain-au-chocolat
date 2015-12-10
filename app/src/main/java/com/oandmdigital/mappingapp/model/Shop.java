package com.oandmdigital.mappingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Shop implements Parcelable {

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
                double longitude,
                double latitude,
                double distance) {

        mName = name;
        mAddress = address;
        mOpeningTimes = openingTimes;
        mUrl = url;
        mTelephone = telephone;
        mImageUrl = imageUrl;
        mRating = rating;
        mLongitude = longitude;
        mLatitude = latitude;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeParcelable(this.mAddress, 0);
        dest.writeTypedList(mOpeningTimes);
        dest.writeString(this.mUrl);
        dest.writeString(this.mTelephone);
        dest.writeString(this.mImageUrl);
        dest.writeDouble(this.mRating);
        dest.writeDouble(this.mLongitude);
        dest.writeDouble(this.mLatitude);
        dest.writeDouble(this.mDistance);
    }

    protected Shop(Parcel in) {
        this.mName = in.readString();
        this.mAddress = in.readParcelable(Address.class.getClassLoader());
        this.mOpeningTimes = in.createTypedArrayList(OpeningTime.CREATOR);
        this.mUrl = in.readString();
        this.mTelephone = in.readString();
        this.mImageUrl = in.readString();
        this.mRating = in.readDouble();
        this.mLongitude = in.readDouble();
        this.mLatitude = in.readDouble();
        this.mDistance = in.readDouble();
    }

    public static final Parcelable.Creator<Shop> CREATOR = new Parcelable.Creator<Shop>() {
        public Shop createFromParcel(Parcel source) {
            return new Shop(source);
        }

        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
}
