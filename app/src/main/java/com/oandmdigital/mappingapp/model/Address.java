package com.oandmdigital.mappingapp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {

    private String mStreet;
    private String mArea;
    private String mPostalCode;


    public Address(String street, String area, String postalCode) {
        mStreet = street;
        mArea = area;
        mPostalCode = postalCode;
    }


    public String getStreet() {
        return mStreet;
    }

    public String getArea() {
        return mArea;
    }

    public String getPostalCode() {
        return mPostalCode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mStreet);
        dest.writeString(this.mArea);
        dest.writeString(this.mPostalCode);
    }

    protected Address(Parcel in) {
        this.mStreet = in.readString();
        this.mArea = in.readString();
        this.mPostalCode = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
